package task_management_app.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import task_management_app.dto.TaskDTO;
import task_management_app.entity.TaskEntity;

@Mapper
public interface TaskMapper {
    TaskMapper INSTANCE = Mappers.getMapper(TaskMapper.class);

    TaskDTO toDTO(TaskEntity task);
    TaskEntity toEntity(TaskDTO taskDTO);
}
