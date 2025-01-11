package task_management_app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import task_management_app.entity.ColumnEntity;

import java.util.List;

public interface ColumnRepository extends JpaRepository<ColumnEntity, Long> {
    List<ColumnEntity> findByBoardId(Long boardId);
}
