package com.codecool.comicsservice.app.api;

import com.codecool.comicsservice.app.model.Comic;
import com.codecool.comicsservice.app.service.ComicsService;
import com.codecool.comicsservice.app.service.JsonService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
public class ComicsServiceREST {

    private JsonService jsonService;
    private ComicsService comicsService;

    public ComicsServiceREST(JsonService jsonService, ComicsService comicsService) {
        this.jsonService = jsonService;
        this.comicsService = comicsService;
    }

    @GetMapping(value = "/comic", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Comic> getComic() throws IOException {
        String xkcdUrl = "https://xkcd.com/" + comicsService.getRandomIntInRange(1929) + "/info.0.json";
        String img = jsonService.parseJson(xkcdUrl, "img");
        String alt = jsonService.parseJson(xkcdUrl, "alt");
        Comic comic = new Comic(alt, img);
        return new ResponseEntity<>(comic, HttpStatus.OK);

    }

}
