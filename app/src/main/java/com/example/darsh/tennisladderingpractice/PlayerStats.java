package com.example.darsh.tennisladderingpractice;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class PlayerStats extends AppCompatActivity {

    Database database;

    private TextView nameView,rankView, winsView, lossesView, improvView, startView, maxView, minView;
    private TextView rankNumView, winsNumView, lossesNumView, improvNumView, startNumView, maxNumView, minNumView;

    private String selectedPlayer;
    private int selectedPlayerID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player_stats);

        nameView = (TextView)findViewById(R.id.nameTextView);
        rankView = (TextView)findViewById(R.id.rankTextView);
        winsView = (TextView)findViewById(R.id.winTextView);
        lossesView = (TextView)findViewById(R.id.lossesTextView);
        improvView = (TextView)findViewById(R.id.improvementTextView);
        startView = (TextView)findViewById(R.id.startTextView);
        maxView = (TextView)findViewById(R.id.maxTextView);
        minView = (TextView)findViewById(R.id.minTextView);

        rankNumView = (TextView)findViewById(R.id.rankNumView);
        winsNumView = (TextView)findViewById(R.id.winsNumView);
        lossesNumView = (TextView)findViewById(R.id.lossesNumView);
        improvNumView = (TextView)findViewById(R.id.improvNumView);
        startNumView = (TextView)findViewById(R.id.startNumView);
        maxNumView = (TextView)findViewById(R.id.maxNumView);
        minNumView = (TextView)findViewById(R.id.minNumView);

        database = new Database(this);

        Intent recievedIntent = getIntent();

        selectedPlayerID = recievedIntent.getIntExtra("playerID", 0);

        selectedPlayer = recievedIntent.getStringExtra("name");

        showPlayerStats();
    }

    public void showPlayerStats(){
        Cursor data = database.getPlayerInfo(selectedPlayer);
        while (data.moveToNext()){
            nameView.setText(data.getString(1));
            rankNumView.setText(data.getString(2));
            winsNumView.setText(data.getString(3));
            lossesNumView.setText(data.getString(4));
            improvNumView.setText(data.getString(5));
            startNumView.setText(data.getString(6));
            maxNumView.setText(data.getString(7));
            minNumView.setText(data.getString(8));
        }
    }
}
