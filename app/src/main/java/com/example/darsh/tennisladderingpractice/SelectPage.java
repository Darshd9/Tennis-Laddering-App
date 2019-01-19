package com.example.darsh.tennisladderingpractice;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class SelectPage extends AppCompatActivity {

    Database database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_select);

        database = new Database(this);

        Button ladderBtn = (Button)findViewById(R.id.ladderbtn);
        ladderBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent startIntent = new Intent(getApplicationContext(), LadderView.class);
                startActivity(startIntent);
            }
        });

        Button matchesBtn = (Button)findViewById(R.id.matchesBtn);
        matchesBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent startIntent = new Intent(getApplicationContext(), EnterMatches.class);
                startActivity(startIntent);
            }
        });

        Button importBtn = (Button)findViewById(R.id.importbtn);
        importBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                database.clearData();
                performFileSearch();



            }
        });
    }
    public void performFileSearch(){
        Intent open = new Intent(Intent.ACTION_OPEN_DOCUMENT);
        open.addCategory(Intent.CATEGORY_OPENABLE);
        open.setType("*/*");
        startActivityForResult(open,1);
    }

    @Override
    //Calling Android File Chooser UI
    public void onActivityResult(int requestCode, int resultCode,Intent resultData) {

        // The ACTION_OPEN_DOCUMENT intent was sent with the request code
        // READ_REQUEST_CODE. If the request code seen here doesn't match, it's the
        // response to some other intent, and the code below shouldn't run at all.

        if (requestCode == 1 && resultCode == Activity.RESULT_OK) {
            // The document selected by the user won't be returned in the intent.
            // Instead, a URI to that document will be contained in the return intent
            // provided to this method as a parameter.
            // Pull that URI using resultData.getData().
            Uri uri = null;
            if (resultData != null) {
                uri = resultData.getData();
                Log.i("Test", "Uri: " +uri.toString());


                try {
                    importTeam(uri);
                    toastMessage("Players Imported");
                }catch (IOException e) {
                    e.printStackTrace();
                }


            }
        }
    }

    private void importTeam(Uri uri) throws IOException {
        InputStream inputStream = getContentResolver().openInputStream(uri);
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        String temp;
        int rank = 0;
        while((temp = reader.readLine()) != null){
            rank++;
            database.addStudent(temp,rank);
        }
    }
    private void toastMessage(String message){
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}
