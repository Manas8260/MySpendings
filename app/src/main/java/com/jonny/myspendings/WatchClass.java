package com.jonny.myspendings;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class WatchClass extends AppCompatActivity {
    TextView total_text;
    ListView listView ;
    Button remove ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_watch_class);

        total_text = findViewById(R.id.total_text);
        listView = findViewById(R.id.list);
        remove = findViewById(R.id.remove);
        final ArrayList<String>  list = new ArrayList<>();
        final ArrayAdapter adapter = new ArrayAdapter(this,R.layout.list_item,list);
        listView.setAdapter(adapter);

        DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("AllSpendings").child("AllStore");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                int totalSpend = 0;
                for(DataSnapshot snapshot1 : snapshot.getChildren()){
                    Information info = snapshot1.getValue(Information.class);
                    String txt = info.getReason()+" : ₹"+info.getAmount();
                    totalSpend += Integer.parseInt(info.getAmount());
                    total_text.setText("TOTAL : ₹"+totalSpend);
                    list.add(txt);
                }
                adapter.notifyDataSetChanged();
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(WatchClass.this, "Error Occured :-"+error, Toast.LENGTH_SHORT).show();
            }
        });




    }
}