package com.jonny.myspendings;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    EditText Reason,Amount;
    Button AddList,WatchList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Reason = findViewById(R.id.Reason);
        Amount = findViewById(R.id.Amount);

        AddList = findViewById(R.id.AddList);
        WatchList = findViewById(R.id.WatchList);


        WatchList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,WatchClass.class));
            }
        });
    }
}