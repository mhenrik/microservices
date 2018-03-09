package com.codecool.enterprise.overcomplicated.controller;

import com.codecool.enterprise.overcomplicated.model.Player;
import com.codecool.enterprise.overcomplicated.model.TictactoeGame;
import com.codecool.enterprise.overcomplicated.service.JsonService;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@Controller
@SessionAttributes({"player", "game", "avatar_uri"})
public class GameController {

    private static final String FUNFACTURL = "http://localhost:60001/quotes/quote";
    private static final String FUNFACTKEY = "quote";

    private static final String COMICURL = "http://localhost:60002/comic";
    private static final String COMICIMGKEY = "url";
    private static final String COMICALTKEY = "alt";

    private static final String AVATARURL = "http://localhost:60003/avatar";
    private static final String AVATARKEY = "url";

    private static final String AIURL = "http://localhost:60004/ai/";
    private static final String AIKEY = "field";

    private JsonService jsonService;

    public GameController(JsonService jsonService) {
        this.jsonService = jsonService;
    }

    @ModelAttribute("player")
    public Player getPlayer() {
        return new Player();
    }

    @ModelAttribute("game")
    public TictactoeGame getGame() {
        String[] newGame = new String[]{"-","-","-","-","-","-","-","-","-",};
        return new TictactoeGame(newGame);
    }

    @ModelAttribute("avatar_uri")
    public String getAvatarUri() throws IOException{
        return jsonService.parseJsonToString(AVATARURL, AVATARKEY);
    }

    @GetMapping(value = "/")
    public String welcomeView(@ModelAttribute Player player) {
        return "welcome";
    }

    @PostMapping(value="/changeplayerusername")
    public String changPlayerUserName(@ModelAttribute Player player) {
        return "redirect:/game";
    }

    @GetMapping(value = "/game")
    public String gameView(@ModelAttribute("player") Player player, Model model) throws IOException {

        String quote = jsonService.parseJsonToString(FUNFACTURL, FUNFACTKEY);

        String img = jsonService.parseJsonToString(COMICURL, COMICIMGKEY);
        String alt = jsonService.parseJsonToString(COMICURL, COMICALTKEY);

        model.addAttribute("funfact", quote);
        model.addAttribute("comic_uri", img);
        model.addAttribute("comic_alt", alt);
        return "game";
    }

    @GetMapping(value = "/game-move")
    public String gameMove(@ModelAttribute("player") Player player, @ModelAttribute("move") int move, @ModelAttribute("game") TictactoeGame tictactoeGame) {
        System.out.println("Player moved "+ move);
        return "redirect:/game";
    }



    @PostMapping(value = "/game/moves", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity move(@RequestParam("table") String table, @RequestParam("id") String id, @ModelAttribute("game") TictactoeGame tictactoeGame) throws IOException {
        int intId = Integer.parseInt(id);
        Map<String, String> winner = new HashMap<>();
        tictactoeGame.playersTurn(intId, "X");
        String[] board = table.split("");
        String endOfRoundOne = tictactoeGame.checkWin();
        if(!endOfRoundOne.equals("C")){
            winner.put("winner", endOfRoundOne);
            tictactoeGame.resetFields();
            return ResponseEntity.ok(winner);
        }
        String url = AIURL + table + "/" + "O";
        JsonNode result = jsonService.parseJson(url, AIKEY);
        int position = result.asInt();
        tictactoeGame.playersTurn(position, "O");
        String endOfRoundTwo = tictactoeGame.checkWin();
        if (!endOfRoundTwo.equals("C")){
            winner.put("winner", endOfRoundTwo);
            tictactoeGame.resetFields();
            return ResponseEntity.ok(winner);
        }
        return ResponseEntity.ok(result);
    }
}
