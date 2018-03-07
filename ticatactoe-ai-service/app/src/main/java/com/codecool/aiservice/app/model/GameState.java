package com.codecool.aiservice.app.model;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class GameState {

    private String[] fields;

    public GameState(String[] fields) {
        this.fields = fields;
    }

    public Integer aisTurn(String turn){
        List<Integer> emptypositions = new ArrayList<>();
        for (int i = 0; i < fields.length; i++){
            if (!fields[i].equals("O") && !fields[i].equals("X")){
                emptypositions.add(i);
            }
        }
        System.out.println(emptypositions.size());
        if (emptypositions.size() == 0){
            return null;
        } else {
            int rand = ThreadLocalRandom.current().nextInt(0, emptypositions.size());
            int position = emptypositions.get(rand);
            fields[position] = turn;
            return position;
        }
    }

    public String[] getFields() {
        return fields;
    }

    public void setFields(String[] fields) {
        this.fields = fields;
    }
}
