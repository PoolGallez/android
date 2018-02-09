package com.example.genji.am013_listview;

import android.database.DataSetObserver;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ListView mListView;
    private ArrayAdapter adapter;
    private List products;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initializeProducts();

        mListView = findViewById(R.id.my_list_view);
        adapter = new ArrayAdapter(this, android.R.layout.test_list_item, products);
        /*
        mListView.setChoiceMode(ListView.CHOICE_MODE_NONE);
        mListView.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        mListView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
        ArrayAdapter adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_checked, products);
        */
        mListView.setAdapter(adapter);

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(MainActivity.this,"Remove : " + products.get(position), Toast.LENGTH_SHORT).show();
                products.remove(position);
                adapter.notifyDataSetChanged();
            }
        });
    }

    public void populate(View view){
        Toast.makeText(this, "RESET", Toast.LENGTH_LONG).show();
        adapter.clear();
        initializeProducts();
        adapter.addAll(products);
        adapter.notifyDataSetChanged();
    }

    private void initializeProducts(){
        products = new ArrayList<String>(Arrays.asList(
                "gioppini",
                "jambonetti",
                "patatine sfizione",
                "tarallini",
                "gallette",
                "frollini plus",
                "cioccolini",
                "secchini",
                "grissinini",
                "patasplash",
                "majopatas",
                "crocchette al sesamo",
                "crocchette alla pancetta",
                "biscotti al miglio e avena"
        ));
    }

}
