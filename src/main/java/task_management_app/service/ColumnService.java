package task_management_app.service;

import task_management_app.dto.ColumnDTO;

import java.util.List;

public interface ColumnService {
    ColumnDTO createColumn(ColumnDTO columnDTO, Long boardId);
    List<ColumnDTO> getColumnsByBoard(Long boardId);
}
