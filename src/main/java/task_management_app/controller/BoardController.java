package task_management_app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import task_management_app.constant.PathGeneric;
import task_management_app.dto.BoardDTO;
import task_management_app.service.BoardService;

import java.nio.charset.StandardCharsets;
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

    @PutMapping(PathGeneric.PATH_UPDATE_BOARD)
    public ResponseEntity<BoardDTO> updateBoard(@PathVariable Long boardId, @RequestBody BoardDTO boardDTO) {
        BoardDTO updatedBoard = boardService.updateBoard(boardId, boardDTO);
        return ResponseEntity.ok(updatedBoard);
    }

    @DeleteMapping(PathGeneric.PATH_DELETE_BOARD)
    public ResponseEntity<Void> deleteBoardsByOwner(@PathVariable Long ownerId) {
        boardService.deleteBoardsByOwner(ownerId);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping(PathGeneric.PATH_DELETE_SPECIFIC_BOARD)
    public ResponseEntity<Void> deleteBoard(@PathVariable Long boardId, @PathVariable Long ownerId) {
        boardService.deleteSpecificBoard(boardId, ownerId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping(PathGeneric.PATH_EXPORT_DATA_BOARD)
    public ResponseEntity<?> exportBoardsWithDetails(@PathVariable Long ownerId) {
        try {
            var exportData = boardService.exportBoardsWithDetails(ownerId);

            ObjectMapper objectMapper = new ObjectMapper();
            String jsonData = objectMapper.writeValueAsString(exportData);

            ByteArrayResource resource = new ByteArrayResource(jsonData.getBytes(StandardCharsets.UTF_8));

            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"export-data-board.json\"")
                    .contentType(MediaType.APPLICATION_OCTET_STREAM)
                    .body(resource);
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error exporting boards: " + e.getMessage());
        }
    }

}
