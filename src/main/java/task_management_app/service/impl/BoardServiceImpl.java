package task_management_app.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import task_management_app.dto.BoardDTO;
import task_management_app.entity.BoardEntity;
import task_management_app.entity.UserEntity;
import task_management_app.mapper.BoardMapper;
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

    private final BoardMapper boardMapper = BoardMapper.INSTANCE;

    @Override
    public BoardDTO createBoard(BoardDTO boardDTO, Long ownerId) {
        UserEntity owner = userRepository.findById(ownerId)
                .orElseThrow(() -> new IllegalArgumentException("Owner not found"));

        BoardEntity boardEntity = boardMapper.toEntity(boardDTO);
        boardEntity.setOwner(owner);
        BoardEntity savedBoard = boardRepository.save(boardEntity);

        return boardMapper.toDTO(savedBoard);
    }

    @Override
    public List<BoardDTO> getBoardsByOwner(Long ownerId) {
        return boardRepository.findByOwnerId(ownerId).stream()
                .map(boardMapper::toDTO)
                .collect(Collectors.toList());
    }
}
