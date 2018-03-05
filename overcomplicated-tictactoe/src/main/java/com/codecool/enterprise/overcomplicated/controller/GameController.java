package com.codecool.enterprise.overcomplicated.controller;

import com.codecool.enterprise.overcomplicated.model.Player;
import com.codecool.enterprise.overcomplicated.model.TictactoeGame;
import com.codecool.enterprise.overcomplicated.service.JsonService;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;

@Controller
@SessionAttributes({"player", "game"})
public class GameController {

    private static final String FUNFACTURL = "http://localhost:60001/quotes/quote";
    private static final String FUNFACTKEY = "quote";

    private static final String COMICURL = "http://localhost:60002/comic";
    private static final String COMICIMGKEY = "url";
    private static final String COMICALTKEY = "alt";

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
        return new TictactoeGame();
    }

    @ModelAttribute("avatar_uri")
    public String getAvatarUri() {
        return "https://robohash.org/codecool";
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

        String quote = jsonService.parseJson(FUNFACTURL, FUNFACTKEY);

        String img = jsonService.parseJson(COMICURL, COMICIMGKEY);
        String alt = jsonService.parseJson(COMICURL, COMICALTKEY);

        model.addAttribute("funfact", quote);
        model.addAttribute("comic_uri", img);
        model.addAttribute("comic_alt", alt);
        return "game";
    }

    @GetMapping(value = "/game-move")
    public String gameMove(@ModelAttribute("player") Player player, @ModelAttribute("move") int move) {
        System.out.println("Player moved " + move);
        return "redirect:/game";
    }
}
