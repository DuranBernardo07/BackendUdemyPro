package plaxi.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import plaxi.backend.entity.Tema;

public interface TemaRepository extends JpaRepository<Tema, Long>{
    
}
