package com.codecool.comicsservice.app.model;

import org.springframework.stereotype.Component;

@Component
public class Comic {

    private String alt;
    private String url;

    public Comic() {
    }

    public Comic(String alt, String url) {
        this.alt = alt;
        this.url = url;
    }

    public String getAlt() {
        return alt;
    }

    public void setAlt(String alt) {
        this.alt = alt;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
