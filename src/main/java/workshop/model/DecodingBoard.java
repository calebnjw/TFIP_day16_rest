package workshop.model;

import jakarta.json.Json;
import jakarta.json.JsonNumber;
import jakarta.json.JsonObject;
import jakarta.json.JsonObjectBuilder;

public class DecodingBoard {
  private int totalCount;

  public DecodingBoard() {
  }

  public int getTotalCount() {
    return totalCount;
  }

  public void setTotalCount(int totalCount) {
    this.totalCount = totalCount;
  }

  public JsonObjectBuilder toJSON() {
    return Json.createObjectBuilder()
        .add("total_count", this.getTotalCount());
  }

  public static DecodingBoard createJson(JsonObject o) {
    DecodingBoard b = new DecodingBoard();

    JsonNumber count = o.getJsonNumber("total_count");

    b.setTotalCount(count.intValue());

    return b;
  }
}
