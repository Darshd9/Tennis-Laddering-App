package com.example.darsh.tennisladderingpractice;

import android.database.Cursor;
import android.nfc.Tag;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class EnterMatches extends AppCompatActivity {

    Database database;

    Player player1, player2;

    Match match;

    private TextView matchView, nextMatchView;
    private EditText playerName1, playerName2, score1, score2;

    private Button enterMatchBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter_matches);

        database = new Database(this);

        matchView = (TextView)findViewById(R.id.matchTextView);
        nextMatchView = (TextView)findViewById(R.id.nextMatchTextView);

        playerName1 = (EditText) findViewById(R.id.playerName1);
        playerName2 = (EditText)findViewById(R.id.playerName2);

        score1 = (EditText)findViewById(R.id.playerScore1);
        score2 = (EditText)findViewById(R.id.playerScore2);

        enterMatchBtn = (Button)findViewById(R.id.enterMatchBtn);
        enterMatchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(checkPlayer(playerName1) == true && checkPlayer(playerName2) == true){
                    if (matchResults() == true){
                        toastMessage("Match Recorded");
                    }

                }else{
                    toastMessage("Error: Invalid Players");
                }


            }
        });

    }

    public boolean matchResults(){
        player1 = fillPlayer(playerName1);
        player2 = fillPlayer(playerName2);

        if(player2.getRank() - player1.getRank() == 1){

            if(Integer.parseInt(score1.getText().toString()) != Integer.parseInt(score2.getText().toString())){

                match = new Match(player1, player2, Integer.parseInt(score1.getText().toString()),
                        Integer.parseInt(score2.getText().toString()));
                match.setWinner();
                match.setLosser();
                database.updateData(match.getWinner());
                database.updateData(match.getLosser());

                nextMatchView.setText(nextMatch(match));
                return true;
            }else{
                toastMessage("Error: Invalid Score of Match");
                return false;

            }

        }else {
            toastMessage("Error: Invalid Matchup");
            return false;
        }


    }

    public Player fillPlayer(EditText player){
        String name = player.getText().toString();
        Cursor data = database.getPlayerInfo(name);

        int rank = 0;
        int win = 0;
        int loss = 0;
        int improvement = 0;
        int startRank = 0;
        int maxRank = 0;
        int minRank = 0;
        while (data.moveToNext()) {
            rank = data.getInt(2);
            win = data.getInt(3);
            loss = data.getInt(4);
            improvement = data.getInt(5);
            startRank = data.getInt(6);
            maxRank = data.getInt(7);
            minRank = data.getInt(8);
        }
        return new Player(name, rank, win, loss, improvement, startRank, maxRank, minRank);


    }

    public String nextMatch(Match match){
        String string = "Next Matches: \n";
        string += match.getWinner().getName() + " vs. " + database.getPlayerName(match.getWinner().getRank() - 2) + "/"
                + database.getPlayerName(match.getWinner().getRank() - 1) + "\n";
        string += match.getLosser().getName() + " vs. " + database.getPlayerName(match.getLosser().getRank() + 1) + "/"
                + database.getPlayerName(match.getLosser().getRank() + 2);
        return string;
    }

    private void toastMessage(String message){
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    public boolean checkPlayer(EditText player){
        String name = player.getText().toString();
        Cursor data = database.getAllData();
        ArrayList<String> playerNames = new ArrayList<>();
        while(data.moveToNext()){
            playerNames.add(data.getString(1));
        }
        boolean c = false;
        int num = 0;
        while(num < playerNames.size()){
            if (playerNames.get(num).equals(name)){
                c = true;
            }
            num++;
        }
        if (c == true){
            return true;
        }else{
            return false;
        }


    }

}
