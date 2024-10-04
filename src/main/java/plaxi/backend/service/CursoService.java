package plaxi.backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import plaxi.backend.dto.ActualizarCursoDto;
import plaxi.backend.dto.CursoDto;
import plaxi.backend.dto.S3ObjectDto;
import plaxi.backend.entity.Curso;
import plaxi.backend.entity.S3Object;
import plaxi.backend.repository.CursoRepository;
import plaxi.backend.repository.CategoriaRepository;
import plaxi.backend.repository.S3ObjectRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CursoService {

    @Autowired
    private CursoRepository cursoRepository;
    @Autowired
    private CategoriaRepository categoriaRepository;

    @Autowired
    private MinioService minioService;

    @Autowired
    private S3ObjectRepository s3ObjectRepository;

    // Obtener todos los cursos activos
    public List<CursoDto> getAllCursos() {
        List<Curso> cursos = cursoRepository.findAllByEstadoTrue();
        return cursos.stream().map(curso -> new CursoDto(
                curso.getIdCurso(),
                curso.getNombre(),
                curso.getDescripcion(),
                curso.getDificultad(),
                curso.getPortada() != null ? curso.getPortada().getUrl() : null,  // URL de la portada si existe
                curso.getEstado(),
                curso.getCategoria() != null ? curso.getCategoria().getIdCategoria() : null  // ID de la categoría
        )).collect(Collectors.toList());
    }
/* 
    // Obtener un curso con detalles
    public CursoDto getCursoConDetalles(Long idCurso) throws Exception {
    Curso curso = cursoRepository.findById(idCurso)
            .orElseThrow(() -> new Exception("Curso no encontrado"));

    if (!curso.getEstado()) {
        throw new Exception("El curso ha sido desactivado.");
    }

    // Mapeamos las entidades relacionadas a sus respectivos DTOs
    List<CategoriaDto> categoriasDto = curso.getCategorias().stream()
            .map(categoria -> new CategoriaDto(categoria.getIdCategoria(), categoria.getNombre()))
            .collect(Collectors.toList());

    List<ConocimientoPrevioDto> conocimientosPreviosDto = curso.getConocimientosPrevios().stream()
            .map(conocimiento -> new ConocimientoPrevioDto(conocimiento.getIdConocimiento(), conocimiento.getDescripcion()))
            .collect(Collectors.toList());

    List<LeccionDto> leccionesDto = curso.getLecciones().stream()
            .map(leccion -> new LeccionDto(leccion.getIdLeccion(), leccion.getTitulo(), leccion.getDescripcion()))
            .collect(Collectors.toList());

    List<TemaDto> temasDto = curso.getTemas().stream()
            .map(tema -> new TemaDto(tema.getIdTema(), tema.getTitulo(), tema.getContenido()))
            .collect(Collectors.toList());

    // Retornar el DTO completo con las relaciones
    return new CursoDto(
            curso.getIdCurso(),
            curso.getNombre(),
            curso.getDescripcion(),
            curso.getDificultad(),
            curso.getPortada() != null ? curso.getPortada().getUrl() : null,
            curso.getEstado(),
            curso.getCategoria().getIdCategoria(),
            categoriasDto,
            conocimientosPreviosDto,
            leccionesDto,
            temasDto
    );
}
*/

    // Obtener un curso por su ID
    public CursoDto getCurso(Long idCurso) throws Exception {
        Curso curso = cursoRepository.findById(idCurso)
                .orElseThrow(() -> new Exception("Curso no encontrado"));

        if (!curso.getEstado()) {
            throw new Exception("El curso ha sido desactivado.");
        }

        String portadaUrl = curso.getPortada() != null ? curso.getPortada().getUrl() : null;  // URL de la portada si existe

        return new CursoDto(
                curso.getIdCurso(),
                curso.getNombre(),
                curso.getDescripcion(),
                curso.getDificultad(),
                portadaUrl,
                curso.getEstado(),
                curso.getCategoria() != null ? curso.getCategoria().getIdCategoria() : null  // ID de la categoría
        );
    }

    // Crear un nuevo curso
public ActualizarCursoDto createCurso(ActualizarCursoDto cursoDto) throws Exception {
        // Validar que la categoría existe
        var categoria = categoriaRepository.findById(cursoDto.getCategoriaId())
                .orElseThrow(() -> new Exception("Categoría no encontrada"));
    
        // Crear una nueva entidad Curso
        Curso curso = new Curso();
        curso.setNombre(cursoDto.getNombre());
        curso.setDescripcion(cursoDto.getDescripcion());
        curso.setDificultad(cursoDto.getDificultad());
        curso.setEstado(cursoDto.getEstado());
        curso.setCategoria(categoria);  // Asignar categoría
    
        // Si el DTO incluye un archivo de portada, subimos la imagen a MinIO y guardamos el enlace
        if (cursoDto.getPortada() != null && !cursoDto.getPortada().isEmpty()) {
            S3ObjectDto s3ObjectDto = minioService.uploadFile(cursoDto.getPortada());
            S3Object s3Object = s3ObjectRepository.findById(s3ObjectDto.getS3ObjectId())
                    .orElseThrow(() -> new Exception("Imagen de portada no encontrada"));
            curso.setPortada(s3Object);  // Asociamos la imagen de portada al curso
        }
    
        curso = cursoRepository.save(curso);  // Guardar el curso en la BD
    
        // Retornar el DTO del curso creado
        return cursoDto;
    }
    
    // Actualizar los detalles de un curso
    public ActualizarCursoDto updateCurso(Long idCurso, ActualizarCursoDto cursoDto) throws Exception {
        Curso curso = cursoRepository.findById(idCurso)
                .orElseThrow(() -> new Exception("Curso no encontrado"));
    
        if (!curso.getEstado()) {
            throw new Exception("El curso ha sido desactivado y no se puede actualizar.");
        }
    
        // Validar que la categoría proporcionada exista
        var categoria = categoriaRepository.findById(cursoDto.getCategoriaId())
                .orElseThrow(() -> new Exception("Categoría no encontrada"));
    
        // Si el DTO incluye un archivo de portada, subimos la imagen a MinIO y guardamos el enlace
        if (cursoDto.getPortada() != null && !cursoDto.getPortada().isEmpty()) {
            S3ObjectDto s3ObjectDto = minioService.uploadFile(cursoDto.getPortada());
            S3Object s3Object = s3ObjectRepository.findById(s3ObjectDto.getS3ObjectId())
                    .orElseThrow(() -> new Exception("Imagen de portada no encontrada"));
            curso.setPortada(s3Object);  // Asociamos la imagen de portada al curso
        }
    
        // Actualizar los datos del curso
        curso.setNombre(cursoDto.getNombre());
        curso.setDescripcion(cursoDto.getDescripcion());
        curso.setDificultad(cursoDto.getDificultad());
        curso.setEstado(cursoDto.getEstado());
        curso.setCategoria(categoria);  // Asignar la nueva categoría
    
        cursoRepository.save(curso);  // Guardar cambios
    
        // Retornar el DTO actualizado
        return cursoDto;
    }
    

    // Borrado lógico del curso (cambia el estado del curso a falso)
    public void deleteCurso(Long idCurso) throws Exception {
        Curso curso = cursoRepository.findById(idCurso)
                .orElseThrow(() -> new Exception("Curso no encontrado"));
        curso.setEstado(false);  // Borrado lógico
        cursoRepository.save(curso);
    }
}
