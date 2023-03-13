package workshop.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import workshop.model.BoardGame;
import workshop.service.BoardGameService;

@RestController
@RequestMapping(path = "/api/boardgame", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
public class BoardGameController {

  @Autowired
  private BoardGameService bgService;

  @PostMapping
  public ResponseEntity<String> createBoardGame(
      @RequestBody BoardGame bg) {
    int insertCnt = bgService.saveGame(bg);
    BoardGame result = new BoardGame();
    result.setId(bg.getId());
    result.setInsertCount(insertCnt);
    if (insertCnt == 0) {
      return ResponseEntity
          .status(HttpStatus.INTERNAL_SERVER_ERROR)
          .contentType(MediaType.APPLICATION_JSON)
          .body(result
              .toJSONInsert()
              .toString());
    } else {
      return ResponseEntity
          .status(HttpStatus.CREATED)
          .contentType(MediaType.APPLICATION_JSON)
          .body(result
              .toJSONInsert()
              .toString());
    }
  }

  @GetMapping(path = "{id}")
  public ResponseEntity<String> getBoardGame(@PathVariable String id) throws IOException {
    BoardGame bg = bgService.findByID(id);
    if (bg == null) {
      return ResponseEntity
          .status(HttpStatus.NOT_FOUND)
          .contentType(MediaType.APPLICATION_JSON)
          .body("");
    } else {
      return ResponseEntity
          .status(HttpStatus.OK)
          .contentType(MediaType.APPLICATION_JSON)
          .body(bg
              .toJSONInsert()
              .toString());
    }
  }

  @PutMapping(path = "{id}")
  public ResponseEntity<String> updateBoardGame(
      @RequestBody BoardGame bg,
      @PathVariable String id,
      @RequestParam(defaultValue = "false") boolean isUpSert) throws IOException {
    BoardGame result = null;
    bg.setUpSert(isUpSert);
    if (!isUpSert) {
      return ResponseEntity
          .status(HttpStatus.BAD_REQUEST)
          .contentType(MediaType.APPLICATION_JSON)
          .body("");
    } else {
      bg.setId(id);
      int updateCount = bgService.updateBoardGame(bg);
      bg.setUpdateCount(updateCount);
      return ResponseEntity
          .status(HttpStatus.OK)
          .contentType(MediaType.APPLICATION_JSON)
          .body(bg
              .toJSONInsert()
              .toString());
    }
  }
}
