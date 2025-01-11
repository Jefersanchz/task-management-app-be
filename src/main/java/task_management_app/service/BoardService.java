package task_management_app.service;

import task_management_app.dto.BoardDTO;

import java.util.List;

public interface BoardService {
    BoardDTO createBoard(BoardDTO boardDTO, Long ownerId);
    List<BoardDTO> getBoardsByOwner(Long ownerId);
    BoardDTO updateBoard(Long boardId, BoardDTO boardDTO);
    void deleteBoardsByOwner(Long ownerId);
    void deleteSpecificBoard(Long boardId, Long ownerId);
  
}
