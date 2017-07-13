package com.example.monpc.prover;

/**
 * Created by Mon pc on 06/03/2017.
 */

public class Score {
    int score = 0;

    public Score(int score) {
        this.score = score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getScore() {
        return score;
    }

    public void addScore(int sc){
        this.score+=sc;
    }

    public void subScore(int sc){
        this.score-=sc;
    }
}
