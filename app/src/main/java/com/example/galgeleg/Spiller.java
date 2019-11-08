package com.example.galgeleg;

public class Spiller {
    private String navn;
    private int score;

    public Spiller(String navn, int score) {
        this.navn = navn;
        this.score = score;
    }

    public String getNavn() {
        return navn;
    }

    public void setNavn(String navn) {
        this.navn = navn;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
}
