package com.example.genji.am002_intent;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class ActivityTwo extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_two);

        // ... trick for landscape
        if(findViewById(R.id.n4) != null){
            TextView n4_land = findViewById(R.id.n4);
            if(savedInstanceState != null){
                n4_land.setText(String.valueOf(savedInstanceState.getInt("n4")));
            }
            return;
        }

        // .. picking widgets
        TextView str1 = findViewById(R.id.str1);
        TextView n1 = findViewById(R.id.n1);
        TextView str2 = findViewById(R.id.str2);
        TextView n2 = findViewById(R.id.n2);
        TextView n3 = findViewById(R.id.n3);
        Button rok = findViewById(R.id.rok);
        Button finish = findViewById(R.id.finish);


        str1.setText(getIntent().getStringExtra("str1"));
        n1.setText(Integer.toString(getIntent().getIntExtra("n1", 0)));

        // get the Bundle object passed in via Intent i using getExtras()
        Bundle bundle = getIntent().getExtras();
        str2.setText(bundle.getString("str2"));
        n2.setText(Integer.toString(bundle.getInt("n2", 0)));

        n3.setText(Integer.toString(getIntent().getIntExtra("n3", 666)));

        rok.setOnClickListener((view) -> {
            // no arguments in constructor !!!!
            Intent i = new Intent();
            // use putExtra() to add new key/value pairs to intent i ---
            i.putExtra("isOK", "RESULT OK");
            //---set the result with OK and the Intent object---
            setResult(RESULT_OK, i);
            finish();
        });

        finish.setOnClickListener((View view) -> {
            Intent i = new Intent(this, ActivityTwo.class);
            // use putExtra() to add new key/value pairs to intent i ---
            i.putExtra("isOK", "RESULT OK FROM FINISH");
            //---set the result with OK and the Intent object---
            finish();
        });
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        // Save UI state changes to the savedInstanceState.
        // This bundle will be passed to onCreate if the process is
        // killed and restarted.
        savedInstanceState.putInt("n4", 444);
    }
}
