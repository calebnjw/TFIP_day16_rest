package workshop.model;

import java.io.Serializable;

import jakarta.json.Json;
import jakarta.json.JsonNumber;
import jakarta.json.JsonObject;
import jakarta.json.JsonObjectBuilder;

public class Rulebook implements Serializable {
  private int totalCount;
  private String file;

  public Rulebook() {
  }

  public int getTotalCount() {
    return totalCount;
  }

  public void setTotalCount(int totalCount) {
    this.totalCount = totalCount;
  }

  public String getFile() {
    return file;
  }

  public void setFile(String file) {
    this.file = file;
  }

  public JsonObjectBuilder toJSON() {
    return Json.createObjectBuilder()
        .add("total_count", this.getTotalCount())
        .add("file", this.getFile());
  }

  public static Rulebook createJson(JsonObject o) {
    Rulebook r = new Rulebook();

    JsonNumber count = o.getJsonNumber("total_count");
    String file = o.getString("file");

    r.setTotalCount(count.intValue());
    r.setFile(file);

    return r;
  }
}
