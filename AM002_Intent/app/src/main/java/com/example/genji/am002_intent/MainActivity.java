package com.example.genji.am002_intent;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

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
            i.putExtra("str1", "Message 1");
            i.putExtra("n1", 111);

            // put extras
            Bundle extras = new Bundle();
            extras.putString("str2", "Message 2");
            extras.putInt("n2", 222);
            i.putExtras(extras);

            // start activity for result
            startActivityForResult(i, ACTIVITY_TWO);
        });
    }

    public void onActivityResult(int requestCode,
                                 int resultCode, Intent data) {
        TextView result = findViewById(R.id.result);
        // check if the request code is 1 (we came from 1 ... )
        if (requestCode == ACTIVITY_TWO) {
            //---if the result is OK---
            if (resultCode == RESULT_OK) {
                result.setText(data.getStringExtra("isOK"));
            } else result.setText("");
        }
    }
}
