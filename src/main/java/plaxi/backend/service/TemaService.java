package plaxi.backend.service;

import jakarta.mail.Multipart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import plaxi.backend.dto.PaginadoDto;
import plaxi.backend.dto.S3ObjectDto;
import plaxi.backend.dto.TemaDto;
import plaxi.backend.entity.S3Object;
import plaxi.backend.entity.Tema;
import plaxi.backend.repository.S3ObjectRepository;
import plaxi.backend.repository.TemaRepository;

import plaxi.backend.entity.Leccion;
import plaxi.backend.repository.LeccionRepository;
import plaxi.backend.dto.LeccionDto;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TemaService {

    @Autowired
    private TemaRepository temaRepository;

    @Autowired
    private LeccionRepository leccionRepository;

    @Autowired
    private MinioService recursoMultimediaService;

    @Autowired
    private S3ObjectRepository s3ObjectRepository;

    // Obtener todos los temas
    public Page<TemaDto> getAllTemas(PaginadoDto paginadoDto) {
        Sort sort = paginadoDto.getSortDir().equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(paginadoDto.getSortBy()).ascending() : Sort.by(paginadoDto.getSortBy()).descending();
        Pageable page = PageRequest.of(paginadoDto.getPage(), paginadoDto.getSize(), sort);
        Page<TemaDto> temas = temaRepository.findAllPaginado(page);

        return temas;
    }

    // Obtener un tema por su ID
    public TemaDto getTema(Long idTema) throws Exception {
        Tema tema = temaRepository.findById(idTema)
                .orElseThrow(() -> new Exception("Tema no encontrado"));

        return new TemaDto(
                tema.getIdTema(),
                tema.getTitulo(),
                tema.getOrden(),
                tema.getDescripcion(),
                tema.getRecursoMultimedia().getUrl(),
                tema.getEstado(),
                tema.getLeccion().getIdLeccion()
        );
    }

    // Obtener temas por curso
    public Page<TemaDto> getTemasByCurso(Long cursoId, PaginadoDto paginadoDto) {

        Sort sort = paginadoDto.getSortDir().equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(paginadoDto.getSortBy()).ascending() : Sort.by(paginadoDto.getSortBy()).descending();
        Pageable page = PageRequest.of(paginadoDto.getPage(), paginadoDto.getSize(), sort);

        Page<TemaDto> temas = temaRepository.findAllByLeccionIdLeccion(cursoId, page);

        return temas;
    }

//    // Obtener lecciones por tema
//    public List<TemaDto> getTemasByLeccion(Long leccionId) {
//        List<Tema> temas = temaRepository.findAllByLeccionIdLeccion(leccionId);
//        return temas.stream().map(tema -> new TemaDto(
//                tema.getIdTema(),
//                tema.getTitulo(),
//                tema.getOrden(),
//                tema.getDescripcion(),
//                tema.getRecursoMultimedia().getUrl(),
//                tema.getEstado(),
//                tema.getLeccion().getIdLeccion()
//        )).collect(Collectors.toList());
//    }

    // Crear un tema
    public void createTema(TemaDto temaDto, MultipartFile file) throws Exception {
        // Subir archivo a MinIO y guardar detalles en la base de datos
        S3ObjectDto s3ObjectDto = recursoMultimediaService.uploadFile(file);
        S3Object s3Object = s3ObjectRepository.findById(s3ObjectDto.getS3ObjectId()).get();
        Tema tema = new Tema();
        tema.setTitulo(temaDto.getTitulo());
        tema.setOrden(temaDto.getOrden());
        tema.setDescripcion(temaDto.getDescripcion());
        tema.setRecursoMultimedia(s3Object);
        tema.setLeccion(leccionRepository.findById(temaDto.getIdTema())
                .orElseThrow(() -> new RuntimeException("Leccion no encontrada")));
        tema.setEstado(true);
        temaRepository.save(tema);
    }

    // Actualizar un tema
    public void updateTema(TemaDto temaDto, MultipartFile file) throws Exception {
        // Subir archivo a MinIO y guardar detalles en la base de datos
        S3ObjectDto s3ObjectDto = recursoMultimediaService.uploadFile(file);
        S3Object s3Object = s3ObjectRepository.findById(s3ObjectDto.getS3ObjectId()).get();

        Tema tema = temaRepository.getOne(temaDto.getIdTema());
        tema.setTitulo(temaDto.getTitulo());
        tema.setOrden(temaDto.getOrden());
        tema.setDescripcion(temaDto.getDescripcion());
        tema.setRecursoMultimedia(s3Object);
        tema.setLeccion(leccionRepository.findById(temaDto.getIdTema())
                .orElseThrow(() -> new RuntimeException("Leccion no encontrada")));
        temaRepository.save(tema);
    }

    // Eliminar un tema
    public void deleteTema(Long idTema) {
        // borrado lógico
        Tema tema = temaRepository.findById(idTema)
                .orElseThrow(() -> new RuntimeException("Tema no encontrado"));
        tema.setEstado(false);
        temaRepository.save(tema);
    }
}
