package com.codecool.comicsservice.app.service;

import org.springframework.stereotype.Service;

import java.util.concurrent.ThreadLocalRandom;
@Service
public class ComicsService {

    public int getRandomIntInRange(int range){
        return ThreadLocalRandom.current().nextInt(1, range + 1);
    }


}
