package com.example.genji.am304_jobservice;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void enqueueWork(View view){
        EditText editText = findViewById(R.id.editText);
        String msg = editText.getText().toString();
        Intent intent = new Intent(this, HelloJobService.class);
        intent.putExtra(HelloJobService.INMSG, msg);
        // startService(intent);
        // for API 26 and forward

        HelloJobService.enqueueWork(this, intent);
    }

}
