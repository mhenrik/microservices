package com.codecool.funfactservice.app.repository;

import com.codecool.funfactservice.app.model.Quote;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuoteRepository extends JpaRepository<Quote, Long> {

}
