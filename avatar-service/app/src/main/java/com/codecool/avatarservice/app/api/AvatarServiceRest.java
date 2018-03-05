package com.codecool.avatarservice.app.api;

import com.codecool.avatarservice.app.model.Avatar;
import com.codecool.avatarservice.app.service.AvatarService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AvatarServiceRest {

    private AvatarService avatarService;

    public AvatarServiceRest(AvatarService avatarService) {
        this.avatarService = avatarService;
    }

    @GetMapping(value = "/avatar", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Avatar> getAvatar(){
        String randomString = avatarService.getRandomString();
        String robotUrl = "https://robohash.org/" + randomString;
        Avatar avatar = new Avatar(robotUrl);
        return new ResponseEntity<>(avatar, HttpStatus.OK);
    }
}
