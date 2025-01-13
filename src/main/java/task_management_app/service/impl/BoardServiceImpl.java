package task_management_app.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import task_management_app.dto.BoardDTO;
import task_management_app.entity.BoardEntity;
import task_management_app.entity.ColumnEntity;
import task_management_app.entity.TaskEntity;
import task_management_app.entity.UserEntity;
import task_management_app.repository.BoardRepository;
import task_management_app.repository.ColumnRepository;
import task_management_app.repository.TaskRepository;
import task_management_app.repository.UserRepository;
import task_management_app.service.BoardService;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class BoardServiceImpl implements BoardService {

    @Autowired
    private BoardRepository boardRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ColumnRepository columnRepository;

    @Autowired
    private TaskRepository taskRepository;

    @Override
    public BoardDTO createBoard(BoardDTO boardDTO, Long ownerId) {
        UserEntity owner = userRepository.findById(ownerId)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        BoardEntity boardEntity = new BoardEntity();
        boardEntity.setName(boardDTO.getName());
        boardEntity.setDescription(boardDTO.getDescription());
        boardEntity.setOwner(owner);

        BoardEntity savedBoard = boardRepository.save(boardEntity);

        BoardDTO createdBoardDTO = new BoardDTO();
        createdBoardDTO.setId(savedBoard.getId());
        createdBoardDTO.setName(savedBoard.getName());
        createdBoardDTO.setDescription(savedBoard.getDescription());
        createdBoardDTO.setOwnerId(savedBoard.getOwner().getId());

        return createdBoardDTO;
    }

    @Override
    public List<BoardDTO> getBoardsByOwner(Long ownerId) {
        return boardRepository.findByOwnerId(ownerId).stream()
                .map(boardEntity -> {
                    BoardDTO boardDTO = new BoardDTO();
                    boardDTO.setId(boardEntity.getId());
                    boardDTO.setName(boardEntity.getName());
                    boardDTO.setDescription(boardEntity.getDescription());
                    boardDTO.setOwnerId(boardEntity.getOwner().getId());
                    return boardDTO;
                })
                .collect(Collectors.toList());
    }

    @Override
    public BoardDTO updateBoard(Long boardId, BoardDTO boardDTO) {
        BoardEntity boardEntity = boardRepository.findById(boardId)
                .orElseThrow(() -> new IllegalArgumentException("Board not found"));

        boardEntity.setName(boardDTO.getName());
        boardEntity.setDescription(boardDTO.getDescription());

        BoardEntity updatedBoard = boardRepository.save(boardEntity);

        BoardDTO updatedBoardDTO = new BoardDTO();
        updatedBoardDTO.setId(updatedBoard.getId());
        updatedBoardDTO.setName(updatedBoard.getName());
        updatedBoardDTO.setDescription(updatedBoard.getDescription());
        updatedBoardDTO.setOwnerId(updatedBoard.getOwner().getId());

        return updatedBoardDTO;
    }

    @Override
    public void deleteBoardsByOwner(Long ownerId) {
        List<BoardEntity> boards = boardRepository.findByOwnerId(ownerId);
        if (boards.isEmpty()) {
            throw new IllegalArgumentException("No boards found for this user");
        }
        boardRepository.deleteAll(boards);
    }

    @Override
    public void deleteSpecificBoard(Long boardId, Long ownerId) {
        BoardEntity boardEntity = boardRepository.findById(boardId)
                .orElseThrow(() -> new IllegalArgumentException("Board not found"));

        if (!boardEntity.getOwner().getId().equals(ownerId)) {
            throw new IllegalArgumentException("Board does not belong to the specified owner");
        }

        boardRepository.delete(boardEntity);
    }

    @Override
    public Map<String, Object> exportBoardsWithDetails(Long ownerId) {
        userRepository.findById(ownerId).orElseThrow(() -> new IllegalArgumentException("User not found"));

        List<BoardEntity> boards = boardRepository.findByOwnerId(ownerId);

        Map<String, Object> exportData = new HashMap<>();
        exportData.put("ownerId", ownerId);
        exportData.put("boards", boards.stream().map(board -> {
            Map<String, Object> boardData = new HashMap<>();
            boardData.put("id", board.getId());
            boardData.put("name", board.getName());
            boardData.put("description", board.getDescription());

            List<ColumnEntity> columns = columnRepository.findByBoardId(board.getId());
            boardData.put("columns", columns.stream().map(column -> {
                Map<String, Object> columnData = new HashMap<>();
                columnData.put("id", column.getId());
                columnData.put("name", column.getName());

                List<TaskEntity> tasks = taskRepository.findByColumnId(column.getId());
                columnData.put("tasks", tasks.stream().map(task -> {
                    Map<String, Object> taskData = new HashMap<>();
                    taskData.put("id", task.getId());
                    taskData.put("title", task.getTitle());
                    taskData.put("description", task.getDescription());
                    return taskData;
                }).collect(Collectors.toList()));

                return columnData;
            }).collect(Collectors.toList()));

            return boardData;
        }).collect(Collectors.toList()));

        return exportData;
    }
}
