package task_management_app.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import task_management_app.dto.TaskDTO;
import task_management_app.entity.ColumnEntity;
import task_management_app.entity.TaskEntity;
import task_management_app.mapper.TaskMapper;
import task_management_app.repository.ColumnRepository;
import task_management_app.repository.TaskRepository;
import task_management_app.service.TaskService;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TaskServiceImpl implements TaskService {

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private ColumnRepository columnRepository;

    private final TaskMapper taskMapper = TaskMapper.INSTANCE;

    @Override
    public TaskDTO createTask(TaskDTO taskDTO, Long columnId) {
        ColumnEntity column = columnRepository.findById(columnId)
                .orElseThrow(() -> new IllegalArgumentException("Column not found"));

        TaskEntity taskEntity = taskMapper.toEntity(taskDTO);
        taskEntity.setColumn(column);
        TaskEntity savedTask = taskRepository.save(taskEntity);

        return taskMapper.toDTO(savedTask);
    }

    @Override
    public List<TaskDTO> getTasksByColumn(Long columnId) {
        return taskRepository.findByColumnId(columnId).stream()
                .map(taskMapper::toDTO)
                .collect(Collectors.toList());
    }
}
