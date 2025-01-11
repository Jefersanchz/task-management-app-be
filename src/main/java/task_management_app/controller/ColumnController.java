package task_management_app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import task_management_app.constant.PathGeneric;
import task_management_app.dto.ColumnDTO;
import task_management_app.service.ColumnService;

import java.util.List;

@RestController
@RequestMapping(PathGeneric.PATH_API_COLUMNS)
@CrossOrigin
public class ColumnController {

    @Autowired
    private ColumnService columnService;

    @PostMapping(PathGeneric.PATH_CREATE_COLUMN)
    public ResponseEntity<ColumnDTO> createColumn(@PathVariable Long boardId, @RequestBody ColumnDTO columnDTO) {
        ColumnDTO createdColumn = columnService.createColumn(columnDTO, boardId);
        return ResponseEntity.ok(createdColumn);
    }

    @GetMapping(PathGeneric.PATH_GET_COLUMNS_BY_BOARD)
    public ResponseEntity<List<ColumnDTO>> getColumnsByBoard(@PathVariable Long boardId) {
        return ResponseEntity.ok(columnService.getColumnsByBoard(boardId));
    }
}
