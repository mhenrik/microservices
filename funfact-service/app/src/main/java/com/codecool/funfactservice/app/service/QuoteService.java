package com.codecool.funfactservice.app.service;

import com.codecool.funfactservice.app.model.Quote;
import com.codecool.funfactservice.app.repository.QuoteRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

@Service
public class QuoteService {

    private QuoteRepository quoteRepository;

    public QuoteService(QuoteRepository quoteRepository){
        this.quoteRepository = quoteRepository;
    }

    public List<Quote> findAll(){
        return quoteRepository.findAll();
    }

    public long count(){
        return quoteRepository.count();
    }


    public Quote getRandomQuote() {
        long size = this.count();
        Long id = ThreadLocalRandom.current().nextLong(1, size);
        Quote quote = quoteRepository.getOne(id);
        return quote;

    }

    public void saveQuote(Quote quote){
        quoteRepository.save(quote);
    }
}
