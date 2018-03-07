package com.codecool.enterprise.overcomplicated.model;

import java.util.Arrays;

public class TictactoeGame {

    private String human = "O";
    private String ai = "X";
    private String[] validPlayers = new String[]{"O", "X"};

    private String[] fields;

    private static TictactoeGame instance;

    private TictactoeGame(String[] fields) {
        this.fields = fields;
    }

    public static TictactoeGame getInstance(String[] fields){
        if (instance == null){
            instance = new TictactoeGame(fields);
        }
        return instance;
    }

    public static TictactoeGame getInstance(){
        if (instance == null){
            instance = new TictactoeGame();
        }
        return instance;
    }


    private TictactoeGame(){
    }

    public void playersTurn(int position, String turn){
        if (!Arrays.asList(validPlayers).contains(turn)){
            throw new IllegalArgumentException("Argument must be either 'X' or 'O'");
        }
        if (position < 0 || position > 8){
            throw new IllegalArgumentException("Position should be between 0 and 8");
        }
        if (!fields[position].equals("O") || !fields[position].equals("X")){
            fields[position] = turn;
        }
    }

    public String checkWin(String x, String o){
        if ((fields[0] + fields[1] + fields[2]).equals(x) ||
                (fields[3] + fields[4] + fields[5]).equals(x) ||
                (fields[6] + fields[7] + fields[8]).equals(x) ||
                (fields[0] + fields[3] + fields[6]).equals(x) ||
                (fields[1] + fields[4] + fields[7]).equals(x) ||
                (fields[2] + fields[5] + fields[8]).equals(x) ||
                (fields[0] + fields[4] + fields[8]).equals(x) ||
                (fields[2] + fields[4] + fields[6]).equals(x)) {
            return "X";
        }
        if ((fields[0] + fields[1] + fields[2]).equals(o) ||
                (fields[3] + fields[4] + fields[5]).equals(o) ||
                (fields[6] + fields[7] + fields[8]).equals(o) ||
                (fields[0] + fields[3] + fields[6]).equals(o) ||
                (fields[1] + fields[4] + fields[7]).equals(o) ||
                (fields[2] + fields[5] + fields[8]).equals(o) ||
                (fields[0] + fields[4] + fields[8]).equals(o) ||
                (fields[2] + fields[4] + fields[6]).equals(o)) {
            return "O";
        }
        return "C";
    }

}
