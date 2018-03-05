package com.codecool.avatarservice.app.service;

import org.springframework.stereotype.Service;
import org.apache.commons.lang3.RandomStringUtils;

@Service
public class AvatarService {

    public String getRandomString(){
        return RandomStringUtils.random(8, true, true);
    }
}
