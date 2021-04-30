package com.bowling.demo.model;

public class Score {

    private int totalScore;
    private int frameScores[];

    public Score(int totalScore, int[] frameScores) {
        this.totalScore = totalScore;
        this.frameScores = frameScores;
    }

    public Score() {
    }

    public int getTotalScore() {
        return totalScore;
    }

    public void setTotalScore(int totalScore) {
        this.totalScore = totalScore;
    }

    public int[] getFrameScores() {
        return frameScores;
    }

    public void setFrameScores(int[] frameScores) {
        this.frameScores = frameScores;
    }

}
