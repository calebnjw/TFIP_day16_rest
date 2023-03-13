package workshop.service;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import workshop.model.BoardGame;
import workshop.repository.BoardGameRepo;

@Service
public class BoardGameService {

  @Autowired
  private BoardGameRepo bgRepo;

  public int saveGame(final BoardGame bg) {
    return bgRepo.saveGame(bg);
  }

  public BoardGame findByID(final String id) throws IOException {
    return bgRepo.findByID(id);
  }

  public int updateBoardGame(final BoardGame bg) {
    return bgRepo.updateBoardGame(bg);
  }
}
