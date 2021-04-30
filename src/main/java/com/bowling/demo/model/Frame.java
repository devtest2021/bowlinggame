package com.bowling.demo.model;

public class Frame {

    private int firstTry;
    private int secondTry;
    private int thirdTry;
    private boolean isScored;

    public Frame () {
        firstTry = 0;
        secondTry =0; 
        thirdTry =0; 
        isScored = false;
    }

    public int getFirstTry() {
        return firstTry;
    }

    public void setFirstTry(int firstTry) {
        this.firstTry = firstTry;
    }

    public int getSecondTry() {
        return secondTry;
    }

    public void setSecondTry(int secondTry) {
        this.secondTry = secondTry;
    }

    public int getThirdTry() {
        return thirdTry;
    }

    public void setThirdTry(int thirdTry) {
        this.thirdTry = thirdTry;
    }

    public boolean isScored() {
        return isScored;
    }

    public void setScored(boolean isScored) {
        this.isScored = isScored;
    }

    public Boolean isStrike() {
        return firstTry == 10;
    }

    public Boolean isSpare() {
        return (firstTry + secondTry) == 10;
    }
    

}
