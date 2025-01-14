package task_management_app.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import task_management_app.dto.ColumnDTO;
import task_management_app.entity.ColumnEntity;

@Mapper(uses = TaskMapper.class)
public interface ColumnMapper {
    ColumnMapper INSTANCE = Mappers.getMapper(ColumnMapper.class);

    ColumnDTO toDTO(ColumnEntity column);
    ColumnEntity toEntity(ColumnDTO columnDTO);
}
