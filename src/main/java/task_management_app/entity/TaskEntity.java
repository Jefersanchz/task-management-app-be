package task_management_app.entity;

import java.util.Date;

import jakarta.persistence.*;
import lombok.*;
@Entity
@Table(name = "tasks")
@Getter
@Setter
@NoArgsConstructor
public class TaskEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    private String description;

    @ManyToOne
    @JoinColumn(name = "column_id", nullable = false)
    private ColumnEntity column;
    private Date createdDate;
    private Date updatedDate;

    @Builder
    public TaskEntity(Long id, String title, String description, ColumnEntity column, Date createdDate, Date updatedDate) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.column = column;
        this.createdDate = createdDate;
        this.updatedDate = updatedDate;
    }
}
