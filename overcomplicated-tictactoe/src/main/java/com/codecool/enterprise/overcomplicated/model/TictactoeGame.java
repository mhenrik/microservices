package com.codecool.enterprise.overcomplicated.model;

import java.util.Arrays;

public class TictactoeGame {

    private String human = "O";
    private String ai = "X";
    private String[] validPlayers = new String[]{"O", "X"};

    private String[] fields;


    public TictactoeGame(String[] fields) {
        this.fields = fields;
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

    public String checkWin(){
        if ((fields[0] + fields[1] + fields[2]).equals("XXX") ||
                (fields[3] + fields[4] + fields[5]).equals("XXX") ||
                (fields[6] + fields[7] + fields[8]).equals("XXX") ||
                (fields[0] + fields[3] + fields[6]).equals("XXX") ||
                (fields[1] + fields[4] + fields[7]).equals("XXX") ||
                (fields[2] + fields[5] + fields[8]).equals("XXX") ||
                (fields[0] + fields[4] + fields[8]).equals("XXX") ||
                (fields[2] + fields[4] + fields[6]).equals("XXX")) {
            return "X";
        }
        if ((fields[0] + fields[1] + fields[2]).equals("OOO") ||
                (fields[3] + fields[4] + fields[5]).equals("OOO") ||
                (fields[6] + fields[7] + fields[8]).equals("OOO") ||
                (fields[0] + fields[3] + fields[6]).equals("OOO") ||
                (fields[1] + fields[4] + fields[7]).equals("OOO") ||
                (fields[2] + fields[5] + fields[8]).equals("OOO") ||
                (fields[0] + fields[4] + fields[8]).equals("OOO") ||
                (fields[2] + fields[4] + fields[6]).equals("OOO")) {
            return "O";
        }
        return "C";
    }

    public String[] getFields() {
        return fields;
    }

    public void resetFields() {
        String[] newGame = new String[]{"-","-","-","-","-","-","-","-","-",};
        this.fields = newGame;
    }
}
