package task_management_app.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import task_management_app.dto.ColumnDTO;
import task_management_app.entity.BoardEntity;
import task_management_app.entity.ColumnEntity;
import task_management_app.mapper.ColumnMapper;
import task_management_app.repository.BoardRepository;
import task_management_app.repository.ColumnRepository;
import task_management_app.service.ColumnService;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ColumnServiceImpl implements ColumnService {

    @Autowired
    private ColumnRepository columnRepository;

    @Autowired
    private BoardRepository boardRepository;

    private final ColumnMapper columnMapper = ColumnMapper.INSTANCE;

    @Override
    public ColumnDTO createColumn(ColumnDTO columnDTO, Long boardId) {
        BoardEntity board = boardRepository.findById(boardId)
                .orElseThrow(() -> new IllegalArgumentException("Board not found"));

        ColumnEntity columnEntity = columnMapper.toEntity(columnDTO);
        columnEntity.setBoard(board);
        ColumnEntity savedColumn = columnRepository.save(columnEntity);

        return columnMapper.toDTO(savedColumn);
    }

    @Override
    public List<ColumnDTO> getColumnsByBoard(Long boardId) {
        return columnRepository.findByBoardId(boardId).stream()
                .map(columnMapper::toDTO)
                .collect(Collectors.toList());
    }
}
