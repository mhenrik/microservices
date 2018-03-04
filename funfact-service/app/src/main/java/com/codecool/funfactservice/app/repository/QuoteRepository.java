package com.codecool.funfactservice.app.repository;

import com.codecool.funfactservice.app.model.Quote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface QuoteRepository extends JpaRepository<Quote, Long> {


}
