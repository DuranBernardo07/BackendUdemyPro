package plaxi.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import plaxi.backend.entity.Curso;

import java.util.List;
import java.util.Optional;

public interface CursoRepository extends JpaRepository<Curso, Long> {

    Optional<Curso> findByNombre(String nombre);
    Optional<Curso> findByIdCurso(Long idCurso);


    List<Curso> findAllByStatusTrue();
}
