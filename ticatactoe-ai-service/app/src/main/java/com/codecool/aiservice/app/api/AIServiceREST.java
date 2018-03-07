package com.codecool.aiservice.app.api;

import com.codecool.aiservice.app.model.GameState;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class AIServiceREST {

    @GetMapping(value = "/ai/{state}/{turn}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<GameState> gamesState(
            @PathVariable("state") String state,
            @PathVariable("turn") String turn){
        String[] table = state.split("");
        GameState gameState = new GameState(table);
        gameState.aisTurn(turn);
        return new ResponseEntity<>(gameState, HttpStatus.OK);
    }
}
