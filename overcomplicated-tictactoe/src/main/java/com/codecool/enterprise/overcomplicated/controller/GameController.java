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
    private static final String AIKEY = "fields";

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
        return TictactoeGame.getInstance();
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
    public ResponseEntity move(@RequestParam("table") String table) throws IOException {
        System.out.println(table);
        String url = AIURL + table + "/" + "X";
        JsonNode result = jsonService.parseJson(url, AIKEY);
        return ResponseEntity.ok(result);
    }
}
