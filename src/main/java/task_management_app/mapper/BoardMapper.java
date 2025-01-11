package task_management_app.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import task_management_app.dto.BoardDTO;
import task_management_app.entity.BoardEntity;

@Mapper
public interface BoardMapper {
    BoardMapper INSTANCE = Mappers.getMapper(BoardMapper.class);

    BoardDTO toDTO(BoardEntity board);
    BoardEntity toEntity(BoardDTO boardDTO);
}
