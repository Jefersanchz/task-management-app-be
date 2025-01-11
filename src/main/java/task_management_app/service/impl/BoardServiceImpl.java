package task_management_app.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import task_management_app.dto.BoardDTO;
import task_management_app.entity.BoardEntity;
import task_management_app.entity.UserEntity;
import task_management_app.repository.BoardRepository;
import task_management_app.repository.UserRepository;
import task_management_app.service.BoardService;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BoardServiceImpl implements BoardService {

    @Autowired
    private BoardRepository boardRepository;

    @Autowired
    private UserRepository userRepository;

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
}


