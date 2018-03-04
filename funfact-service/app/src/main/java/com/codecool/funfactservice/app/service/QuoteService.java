package com.codecool.funfactservice.app.service;

import com.codecool.funfactservice.app.model.Quote;
import com.codecool.funfactservice.app.repository.QuoteRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;
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


    public Quote getRandomQuote(){
        return quoteRepository.getOne(ThreadLocalRandom.current().nextLong(quoteRepository.count()));
    }

    public void saveQuote(Quote quote){
        quoteRepository.save(quote);
    }
}
