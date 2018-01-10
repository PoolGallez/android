package com.example.genji.am002_intent;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    static final int ACTIVITY_TWO = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btn = findViewById(R.id.button);
        btn.setOnClickListener((view)-> {

                Intent i = new Intent(this, ActivityTwo.class);

                // put extra
                i.putExtra("str1", "Message Example");
                i.putExtra("n1", 25);

                // put extras
                Bundle extras = new Bundle();
                extras.putString("str2", "This is another string");
                extras.putInt("n2", 666);
                i.putExtras(extras);

                startActivityForResult(i, ACTIVITY_TWO);
        });
    }

    public void onActivityResult(int requestCode,
                                 int resultCode, Intent data) {
        // check if the request code is 1 (we came from 1 ... )
        if (requestCode == ACTIVITY_TWO) {
            //---if the result is OK---
            if (resultCode == RESULT_OK) {
                TextView textView = findViewById(R.id.textView);
                textView.setText(getResources().getText(R.string.main_activity_returned));
            }
        }
    }
}
