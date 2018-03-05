package com.codecool.funfactservice.app.api;

import com.codecool.funfactservice.app.model.Quote;
import com.codecool.funfactservice.app.service.QuoteService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class QuoteServiceREST {

    private QuoteService quoteService;

    public QuoteServiceREST(QuoteService quoteService) {
        this.quoteService = quoteService;
    }

    @GetMapping(value = "/quotes", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<List<Quote>> getAllQuotes(){
        return new ResponseEntity<>(quoteService.findAll(), HttpStatus.OK);

    }

    @GetMapping(value = "/quotes/quote", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Quote> getRandomQuote() {
        Quote quote = quoteService.getRandomQuote();
        return new ResponseEntity<>(quote, HttpStatus.OK);
    }

}
