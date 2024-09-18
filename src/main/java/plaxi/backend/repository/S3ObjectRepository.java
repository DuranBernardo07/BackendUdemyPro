package plaxi.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import plaxi.backend.entity.S3Object;

public interface S3ObjectRepository extends JpaRepository<S3Object, Long> {
}
