package com.example.darsh.tennisladderingpractice;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class LadderView extends AppCompatActivity {

    Database database;
    private ListView listView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view_test);

        listView = (ListView)findViewById(R.id.ladderListView);
        database = new Database(this);

        populateListView();

    }

    private void populateListView(){
        Cursor data = database.getAllData();
        ArrayList<Player> playerList = new ArrayList<>();
        while(data.moveToNext()){
            playerList.add(new Player(data.getString(1), data.getInt(2), data.getInt(3), data.getInt(4),
                    data.getInt(5), data.getInt(6), data.getInt(7), data.getInt(8)));
        }

        Collections.sort(playerList, new Comparator<Player>() {
            @Override
            public int compare(Player p1, Player p2) {
                return Integer.valueOf(p1.getRank()).compareTo(p2.getRank());
            }
        });

        final ListAdapter adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, playerList);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Player player = (Player) adapterView.getItemAtPosition(i);
                String name = player.getName();
                Intent playerStats = new Intent(LadderView.this, PlayerStats.class);
                playerStats.putExtra("name", name);
                startActivity(playerStats);
            }
        });
    }

}
