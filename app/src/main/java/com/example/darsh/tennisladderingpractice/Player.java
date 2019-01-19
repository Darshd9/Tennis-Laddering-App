package com.example.darsh.tennisladderingpractice;

import android.app.Activity;

/**
 * Created by darsh on 2/13/2018.
 */

public class Player {
    private String name;
    private int rank;
    private int win;
    private int lose;
    private int improvement;
    private int startRank;
    private int maxRank;
    private int minRank;




    public Player(String name, int rank, int win, int lose, int improvement, int startRank, int maxRank, int minRank){
        this.name = name;
        this.rank = rank;
        this.maxRank = maxRank;
        this.minRank = minRank;
        this.win = win;
        this.lose = lose;
        this.startRank = startRank;
        this.improvement = improvement;

    }

    public String getName(){
        return name;
    }
    public void setName(String name){
        this.name = name;
    }

    public void setRank(int rank){
        this.rank = rank;
        if(rank < maxRank){
            this.maxRank = rank;
        }
        if(rank > minRank){
            this.minRank = rank;
        }
    }

    public int getRank(){
        return rank;
    }
    public int getMaxRank(){
        return maxRank;
    }
    public int getMinRank(){
        return minRank;
    }
    public int getStartRank(){
        return startRank;
    }
    public void addWin(){
        win++;
    }
    public void addLose(){
        lose++;
    }
    public int getWin(){
        return win;
    }
    public int getLose(){
        return lose;
    }
    public int getImprovement(){
        improvement = startRank - rank;
        return improvement;
    }
    public String toString(){
        return rank + " - " + name;
    }

}
