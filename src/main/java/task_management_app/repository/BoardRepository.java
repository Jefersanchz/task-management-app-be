package task_management_app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import task_management_app.entity.BoardEntity;

import java.util.List;

public interface BoardRepository extends JpaRepository<BoardEntity, Long> {
    List<BoardEntity> findByOwnerId(Long ownerId);

}
