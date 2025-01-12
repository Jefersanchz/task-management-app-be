package task_management_app.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import task_management_app.dto.TaskDTO;
import task_management_app.entity.TaskEntity;

@Mapper
public interface TaskMapper {
    TaskMapper INSTANCE = Mappers.getMapper(TaskMapper.class);

    @Mapping(source = "column.id", target = "columnId") 
    TaskDTO toDTO(TaskEntity task);

    @Mapping(source = "columnId", target = "column.id") 
    TaskEntity toEntity(TaskDTO taskDTO);
}
