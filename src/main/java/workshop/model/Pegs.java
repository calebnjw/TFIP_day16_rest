package workshop.model;

import java.util.LinkedList;
import java.util.List;

import jakarta.json.Json;
import jakarta.json.JsonArray;
import jakarta.json.JsonArrayBuilder;
import jakarta.json.JsonNumber;
import jakarta.json.JsonObject;
import jakarta.json.JsonObjectBuilder;

public class Pegs {
  private int totalCount;
  private List<Type> types;

  public Pegs() {
  }

  public int getTotalCount() {
    return totalCount;
  }

  public void setTotalCount(int totalCount) {
    this.totalCount = totalCount;
  }

  public List<Type> getTypes() {
    return types;
  }

  public void setTypes(List<Type> types) {
    this.types = types;
  }

  public JsonObjectBuilder toJSON() {
    JsonArrayBuilder arrayBuilder = Json.createArrayBuilder();
    List<JsonObjectBuilder> listOfTypes = this.getTypes()
        .stream()
        .map(t -> t.toJSON())
        .toList();

    for (JsonObjectBuilder x : listOfTypes) {
      arrayBuilder.add(x);
    }

    // object builder will be built at the top level
    return Json.createObjectBuilder()
        .add("total_count", this.getTotalCount())
        .add("types", arrayBuilder);
  }

  public static Pegs createJson(JsonObject o) {
    Pegs p = new Pegs();
    List<Type> result = new LinkedList<>();

    JsonNumber count = o.getJsonNumber("total_count");
    JsonArray types = o.getJsonArray("types");

    for (int i = 0; i < types.size(); i++) {
      JsonObject x = types.getJsonObject(i);
      Type t = Type.createJson(x);
      result.add(t);
    }

    p.setTotalCount(count.intValue());
    p.setTypes(result);

    return p;
  }
}
