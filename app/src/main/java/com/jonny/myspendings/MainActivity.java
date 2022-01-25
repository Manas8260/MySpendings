package com.jonny.myspendings;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    EditText Reason,Amount;
    Button AddList,WatchList,logout;

    FirebaseDatabase db;
    FirebaseAuth fAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);


        fAuth = FirebaseAuth.getInstance();
        Reason = findViewById(R.id.Reason);
        Amount = findViewById(R.id.Amount);
        logout = findViewById(R.id.logout);
        AddList = findViewById(R.id.AddList);
        WatchList = findViewById(R.id.WatchList);

        db = db.getInstance();
        AddList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String reason = Reason.getText().toString() ,amount = Amount.getText().toString();
                if(reason.isEmpty() || amount.isEmpty()){
                    Toast.makeText(MainActivity.this, "Please Enter All Details", Toast.LENGTH_SHORT).show();
                }else{
                    HashMap<String,Object> listss = new HashMap<>();
                    listss.put("reason",reason);
                    listss.put("amount",amount);

                    FirebaseUser user = fAuth.getCurrentUser();
                    
                    db.getReference().child("AllSpendings").child(user.getUid()).push().updateChildren(listss);
                    Toast.makeText(MainActivity.this, "Added To the LIST", Toast.LENGTH_SHORT).show();
                }
            }
        });

        WatchList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,WatchClass.class));
            }
        });

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(MainActivity.this, Login.class));
                finish();
            }
        });
    }
}