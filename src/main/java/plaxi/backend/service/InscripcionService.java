package plaxi.backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import plaxi.backend.dto.InscripcionDto;

import plaxi.backend.dto.InscripcionResponseDto;
import plaxi.backend.entity.Curso;
import plaxi.backend.entity.Inscripcion;
import plaxi.backend.entity.Usuario;
import plaxi.backend.repository.CursoRepository;
import plaxi.backend.repository.InscripcionRepository;
import plaxi.backend.repository.UsuarioRepository;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class InscripcionService {

    @Autowired
    private InscripcionRepository inscripcionRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private CursoRepository cursoRepository;

    private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    public List<InscripcionResponseDto> getAllInscripciones() {
        List<Inscripcion> inscripciones = inscripcionRepository.findAll();
        List<InscripcionResponseDto> responseList = new ArrayList<>();

        for (Inscripcion inscripcion : inscripciones) {
            responseList.add(convertToDto(inscripcion));
        }
        return responseList;
    }

    public Optional<InscripcionResponseDto> getInscripcionById(Long id) {
        Optional<Inscripcion> inscripcionOpt = inscripcionRepository.findById(id);
        return inscripcionOpt.map(this::convertToDto);
    }

    public InscripcionResponseDto createInscripcion(InscripcionDto inscripcionDto) {
        Inscripcion inscripcion = new Inscripcion();

        // Buscar el usuario y curso correspondientes
        Optional<Usuario> usuarioOpt = usuarioRepository.findById(inscripcionDto.getUsuarioId());
        Optional<Curso> cursoOpt = cursoRepository.findById(inscripcionDto.getCursoId());

        if (usuarioOpt.isPresent() && cursoOpt.isPresent()) {

            // verificar si el usuario ya está inscrito en el curso
            Boolean verificarInscripcion = inscripcionRepository.findByUsuarioAndCurso(usuarioOpt.get(), cursoOpt.get());
            if(verificarInscripcion){
                throw new IllegalArgumentException("El usuario ya está inscrito en el curso");
            }

            inscripcion.setUsuario(usuarioOpt.get());
            inscripcion.setCurso(cursoOpt.get());
        } else {
            throw new IllegalArgumentException("Usuario o Curso no encontrado");
        }

        // Guardar la inscripción con fecha y estado por defecto
        Inscripcion savedInscripcion = inscripcionRepository.save(inscripcion);
        return convertToDto(savedInscripcion);
    }

    public InscripcionResponseDto updateInscripcion(Long id, InscripcionDto inscripcionDto) {
        Optional<Inscripcion> optionalInscripcion = inscripcionRepository.findById(id);

        if (optionalInscripcion.isPresent()) {
            Inscripcion inscripcion = optionalInscripcion.get();
            // Actualizar solo las relaciones, no la fecha ni el estado
            Optional<Usuario> usuarioOpt = usuarioRepository.findById(inscripcionDto.getUsuarioId());
            Optional<Curso> cursoOpt = cursoRepository.findById(inscripcionDto.getCursoId());

            if (usuarioOpt.isPresent() && cursoOpt.isPresent()) {
                inscripcion.setUsuario(usuarioOpt.get());
                inscripcion.setCurso(cursoOpt.get());
                Inscripcion updatedInscripcion = inscripcionRepository.save(inscripcion);
                return convertToDto(updatedInscripcion);
            } else {
                throw new IllegalArgumentException("Usuario o Curso no encontrado");
            }
        }
        return null;
    }

    public boolean deleteInscripcion(Long id) {
        if (inscripcionRepository.existsById(id)) {
            inscripcionRepository.deleteById(id);
            return true;
        }
        return false;
    }

    private InscripcionResponseDto convertToDto(Inscripcion inscripcion) {
        // Extraer el ID del creador del curso
        Long usuarioCreadorId = inscripcion.getCurso().getUsuarioCreador() != null ? inscripcion.getCurso().getUsuarioCreador().getIdUsuario() : null;

        return new InscripcionResponseDto(
                inscripcion.getIdInscripcion(),
                dateFormat.format(inscripcion.getFechaInscripcion()),
                inscripcion.getEstadoInscripcion(),
                inscripcion.getUsuario().getIdUsuario(),
                inscripcion.getUsuario().getUsername(),
                inscripcion.getUsuario().getGmail(),
                inscripcion.getCurso().getIdCurso(),
                inscripcion.getCurso().getNombre(),
                usuarioCreadorId
        );
    }

}
