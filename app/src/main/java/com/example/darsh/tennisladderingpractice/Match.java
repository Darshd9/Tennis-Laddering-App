package com.example.darsh.tennisladderingpractice;

/**
 * Created by darsh on 3/16/2018.
 */

public class Match {

    private Player player1;
    private Player player2;
    private int score1;
    private int score2;

    public Match(Player player1, Player player2, int score1, int score2){
        this.player1 = player1;
        this.player2 = player2;
        this.score1 = score1;
        this.score2 = score2;
    }

    public void setWinner(){
        if (score1 > score2){
            player1.addWin();
        }else{
            player2.addWin();
            player2.setRank(player2.getRank() - 1);
        }
    }
    public Player getWinner(){
        if (score1 > score2){
            return player1;
        }else{
            return player2;
        }
    }

    public void setLosser(){
        if (score1 > score2){
            player2.addLose();
        }else{
            player1.addLose();
            player1.setRank(player1.getRank() + 1);
        }
    }

    public Player getLosser(){
        if(score1 > score2){
            return player2;
        }else{
            return player1;
        }
    }


}
