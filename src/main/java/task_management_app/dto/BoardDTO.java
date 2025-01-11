package task_management_app.dto;

import lombok.Data;

@Data
public class BoardDTO {
    private Long id;
    private String name;
    private String description;
    private Long ownerId;
}
