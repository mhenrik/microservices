package com.codecool.funfactservice.app.model;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "QUOTE")
@NoArgsConstructor
@ToString
public class Quote {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private @Getter @Setter long id;

    @Column(name = "quote")
    private @Getter @Setter String chuckQuote;

    public Quote(String chuckQuote){
        this.chuckQuote = chuckQuote;
    }

}
