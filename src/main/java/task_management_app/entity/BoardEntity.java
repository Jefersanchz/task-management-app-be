package task_management_app.entity;

import java.sql.Date;
import java.util.List;


import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Entity
@Table(name = "boards")
@Getter
@Setter
@NoArgsConstructor
public class BoardEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    private String description;

    @ManyToOne
    @JoinColumn(name = "owner_id", nullable = false)
    private UserEntity owner;

    @OneToMany(mappedBy = "board", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ColumnEntity> columns;

    private Date createdDate;
    private Date updatedDate;

    @Builder
    public BoardEntity(Long id, String name, String description, UserEntity owner, Date createdDate, Date updatedDate) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.owner = owner;
        this.createdDate = createdDate;
        this.updatedDate = updatedDate;
    }

}
