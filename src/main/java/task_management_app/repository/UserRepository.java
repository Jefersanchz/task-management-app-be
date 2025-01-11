package task_management_app.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

import task_management_app.entity.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
    Optional<UserEntity> findByUsername(String username);
}


