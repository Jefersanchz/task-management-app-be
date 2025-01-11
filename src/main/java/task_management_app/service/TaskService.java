package task_management_app.service;

import task_management_app.dto.TaskDTO;

import java.util.List;

public interface TaskService {
    TaskDTO createTask(TaskDTO taskDTO, Long columnId);
    List<TaskDTO> getTasksByColumn(Long columnId);
    List<TaskDTO> getAllTasks();
    TaskDTO updateTask(Long taskId, TaskDTO taskDTO);


}
