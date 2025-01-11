package task_management_app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import task_management_app.entity.TaskEntity;

import java.util.List;

public interface TaskRepository extends JpaRepository<TaskEntity, Long> {
    List<TaskEntity> findByColumnId(Long columnId);
}
