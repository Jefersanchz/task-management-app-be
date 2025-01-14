package task_management_app.dto;

import java.util.List;

import lombok.Data;

@Data
public class ColumnDTO {
    private Long id;
    private String name;
    private int position;
    private List<TaskDTO> tasks;
}
