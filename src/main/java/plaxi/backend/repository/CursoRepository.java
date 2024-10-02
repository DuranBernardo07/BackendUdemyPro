package plaxi.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import plaxi.backend.entity.Curso;

public interface CursoRepository extends JpaRepository<Curso, Long> {

}
