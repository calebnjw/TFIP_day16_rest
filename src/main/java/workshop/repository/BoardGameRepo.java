package workshop.repository;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import workshop.model.BoardGame;

@Repository
public class BoardGameRepo {

  @Autowired
  private RedisTemplate<String, String> template;

  public int saveGame(final BoardGame bg) {
    template.opsForValue().set(bg.getId(), bg.toJSON().toString());
    String result = (String) template.opsForValue().get(bg.getId());
    if (result != null) {
      return 1;
    } else {
      return 0;
    }
  }

  public BoardGame findByID(final String id) throws IOException {
    String jsonStringValue = (String) template
        .opsForValue()
        .get(id);
    BoardGame bg = BoardGame.create(jsonStringValue);
    return bg;
  }

  public int updateBoardGame(final BoardGame bg) {
    String result = (String) template.opsForValue().get(bg.getId());
    if (bg.isUpSert()) {
      if (result != null) {
        template.opsForValue().set(bg.getId(), bg.toJSON().toString());
      } else {
        bg.setId(bg.generateId(8));
        template.opsForValue().setIfAbsent(bg.getId(), bg.toJSON().toString());
      }
    } else {
      if (result != null) {
        template.opsForValue().set(bg.getId(), bg.toJSON().toString());
      }
    }

    result = (String) template.opsForValue().get(bg.getId());

    if (result != null) {
      return 1;
    } else {
      return 0;
    }
  }
}
