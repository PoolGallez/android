package com.example.genji.am005_map;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onClick(View view) {
        Intent i = new Intent(Intent.ACTION_VIEW);
        switch(view.getId()){
            case R.id.map:
                String url_position = "geo:45.495403,12.257509";
                i.setData(Uri.parse(url_position));
                break;
            case R.id.browser:
                String url = "http://www.itiszuccante.gov.it";
                i.setData(Uri.parse(url));
        }
        startActivity(i);
    }
}
