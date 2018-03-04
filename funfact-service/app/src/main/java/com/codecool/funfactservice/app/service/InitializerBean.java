package com.codecool.funfactservice.app.service;

import com.codecool.funfactservice.app.model.Quote;
import org.springframework.stereotype.Component;

@Component
public class InitializerBean {

    public InitializerBean(QuoteService quoteService){
        quoteService.saveQuote(new Quote("Chuck Norris has a grizzly bear carpet in his room. The bear isn't dead it is just afriad to move."));
        quoteService.saveQuote(new Quote("Chuck Norris and Superman once fought each other on a bet. The loser had to start wearing his underwear on the outside of his pants."));
        quoteService.saveQuote(new Quote("Chuck Norris died 20 years ago, Death just hasn't built up the courage to tell him yet."));
        quoteService.saveQuote(new Quote("Chuck Norris doesn't flush the toilet...he scares the shit out of it."));
        quoteService.saveQuote(new Quote("Before going to bed, the Boogeyman always checks his closet for Chuck Norris."));
        quoteService.saveQuote(new Quote("Chuck Norris can divide by zero."));
        quoteService.saveQuote(new Quote("Chuck Norris ordered a Big Mac at Burger King, and got one."));
        quoteService.saveQuote(new Quote("Chuck Norris played Russian Roulette with a fully loaded gun and won."));
        quoteService.saveQuote(new Quote("Some people wear Superman pajamas. Superman wears Chuck Norris pajamas."));
        quoteService.saveQuote(new Quote("Chuck Norris can slam a revolving door."));
        quoteService.saveQuote(new Quote("Chuck Norris can judge a book by its cover."));
    }
}
