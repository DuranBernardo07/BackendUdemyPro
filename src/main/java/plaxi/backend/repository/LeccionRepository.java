package plaxi.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import plaxi.backend.entity.Leccion;

public interface LeccionRepository extends JpaRepository<Leccion, Long>{
    
}
