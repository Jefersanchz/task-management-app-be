package task_management_app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import task_management_app.constant.PathGeneric;
import task_management_app.dto.TaskDTO;
import task_management_app.service.TaskService;

import java.util.List;

@RestController
@RequestMapping(PathGeneric.PATH_API_TASKS)
@CrossOrigin
public class TaskController {

    @Autowired
    private TaskService taskService;

    @PostMapping(PathGeneric.PATH_CREATE_TASK)
    public ResponseEntity<TaskDTO> createTask(@PathVariable Long columnId, @RequestBody TaskDTO taskDTO) {
        TaskDTO createdTask = taskService.createTask(taskDTO, columnId);
        return ResponseEntity.ok(createdTask);
    }

    @GetMapping(PathGeneric.PATH_GET_TASKS_BY_COLUMN)
    public ResponseEntity<List<TaskDTO>> getTasksByColumn(@PathVariable Long columnId) {
        return ResponseEntity.ok(taskService.getTasksByColumn(columnId));
    }

    @GetMapping(PathGeneric.PATH_GET_ALL_TASKS)
    public ResponseEntity<List<TaskDTO>> getAllTasks() {
        return ResponseEntity.ok(taskService.getAllTasks());
    }

    @PutMapping(PathGeneric.PATH_UPDATE_TASK)
    public ResponseEntity<TaskDTO> updateTask(@PathVariable Long taskId, @RequestBody TaskDTO taskDTO) {
        TaskDTO updatedTask = taskService.updateTask(taskId, taskDTO);
        return ResponseEntity.ok(updatedTask);
    }

    @DeleteMapping(PathGeneric.PATH_DELETE_TASK)
    public ResponseEntity<Void> deleteTask(@PathVariable Long taskId) {
        taskService.deleteTask(taskId);
        return ResponseEntity.noContent().build();
    }

}
