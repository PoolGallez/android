package com.example.genji.am301_lambda;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private boolean clear = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EditText edit = findViewById(R.id.edit);
        Button button = findViewById(R.id.button);
        button.setOnClickListener((View view) -> {
            if (clear) {
                edit.getText().clear();
                view.setBackgroundColor(getResources().getColor(R.color.yellow));
                ((Button) view).setText(getResources().getText(R.string.click_me));
            } else {
                String s = edit.getText().toString();
                Toast.makeText(this, s, Toast.LENGTH_LONG).show();
                view.setBackgroundColor(getResources().getColor(R.color.colorAccent));
                ((Button) view).setText(getResources().getText(R.string.clear));
            }
            clear = !clear;
        });
        /* more short
        button.setOnClickListener((view) -> {
            ...
        });
         */
        /* old fashion
        NB you have to set final button and edit
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ...
            }
        });
         */

    }
}
