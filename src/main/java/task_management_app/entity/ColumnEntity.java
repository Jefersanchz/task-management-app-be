package task_management_app.entity;

import java.util.List;


import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Entity
@Table(name = "columns")
@Getter
@Setter
@NoArgsConstructor
public class ColumnEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @ManyToOne
    @JoinColumn(name = "board_id", nullable = false)
    private BoardEntity board;

    @OneToMany(mappedBy = "column", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<TaskEntity> tasks;

    private int position;

    @Builder
    public ColumnEntity(Long id, String name, BoardEntity board, int position) {
        this.id = id;
        this.name = name;
        this.board = board;
        this.position = position;
    }

}
