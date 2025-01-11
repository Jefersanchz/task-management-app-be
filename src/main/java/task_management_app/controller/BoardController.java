package task_management_app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import task_management_app.constant.PathGeneric;
import task_management_app.dto.BoardDTO;
import task_management_app.service.BoardService;

import java.util.List;

@RestController
@RequestMapping(PathGeneric.PATH_API_BOARDS)
@CrossOrigin
public class BoardController {

    @Autowired
    private BoardService boardService;

   
    @PostMapping(PathGeneric.PATH_CREATE_BOARD)
    public ResponseEntity<BoardDTO> createBoard(@RequestBody BoardDTO boardDTO) {
        BoardDTO createdBoard = boardService.createBoard(boardDTO, boardDTO.getOwnerId());
        return ResponseEntity.ok(createdBoard);
    }

    @GetMapping(PathGeneric.PATH_GET_BOARDS_BY_OWNER)
    public ResponseEntity<List<BoardDTO>> getBoardsByOwner(@PathVariable Long ownerId) {
        return ResponseEntity.ok(boardService.getBoardsByOwner(ownerId));
    }
}
