package workshop.model;

import java.io.Serializable;

import jakarta.json.Json;
import jakarta.json.JsonObject;
import jakarta.json.JsonObjectBuilder;

public class Pieces implements Serializable {
  private DecodingBoard decodingBoard;
  private Pegs pegs;
  private Rulebook rulebook;

  public Pieces() {
  }

  public DecodingBoard getDecodingBoard() {
    return decodingBoard;
  }

  public void setDecodingBoard(DecodingBoard decodingBoard) {
    this.decodingBoard = decodingBoard;
  }

  public Pegs getPegs() {
    return pegs;
  }

  public void setPegs(Pegs pegs) {
    this.pegs = pegs;
  }

  public Rulebook getRulebook() {
    return rulebook;
  }

  public void setRulebook(Rulebook rulebook) {
    this.rulebook = rulebook;
  }

  public JsonObjectBuilder toJSON() {
    return Json.createObjectBuilder()
        .add("decoding_board", this.getDecodingBoard().toJSON())
        .add("pegs", this.getPegs().toJSON())
        .add("rulebook", this.getRulebook().toJSON());
  }

  public static Pieces createJson(JsonObject o) {
    Pieces p = new Pieces();

    JsonObject decodingBoard = o.getJsonObject("decoding_board");
    JsonObject pegs = o.getJsonObject("pegs");
    JsonObject rulebook = o.getJsonObject("rulebook");

    p.setDecodingBoard(DecodingBoard.createJson(decodingBoard));
    p.setPegs(Pegs.createJson(pegs));
    p.setRulebook(Rulebook.createJson(rulebook));

    return p;
  }

}
