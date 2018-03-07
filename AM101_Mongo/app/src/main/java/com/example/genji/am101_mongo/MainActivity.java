package com.example.genji.am101_mongo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    public static final String BASE_URL = "http://192.168.1.2:8080";
    private EmbeddedService mService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.d("MainActivity", "create Retrofit Service");
        mService = RetrofitClient.getClient(BASE_URL).create(EmbeddedService.class);


        Button btn = findViewById(R.id.button);
        btn.setOnClickListener((View view) -> {
            Log.d("MainActivity", "call loadCars()");
            loadProducts();
        });
        
    }

    public void loadProducts() {
        mService.getEmbedded().enqueue(new Callback<Embedded>() {

            private ArrayList<Product> products = new ArrayList<>();

            @Override
            public void onResponse(Call<Embedded> call, Response<Embedded> response) {

                if(response.isSuccessful()) {
                    Log.d("MainActivity", "posts loaded from API");
                    Embedded embedded = response.body();
                    products = embedded.getProducts();

                    MainActivity.this.showProducts(products);

                } else {
                    int statusCode  = response.code();
                    // handle request errors depending on status code
                    Log.d("MainActivity", "result code is: " + statusCode);
                }
            }

            @Override
            public void onFailure(Call<Embedded> call, Throwable t) {
                showErrorMessage();
                Log.d("MainActivity", "error loading from API");

            }
        });
    }

    void showProducts(ArrayList<Product> products){

        TextView carList = findViewById(R.id.product_list);
        String list ="";
        for(Product product : products){
            list += "item: " + product.getItem() +", status: " + product.getStatus() + "\n";
        }
        carList.setText(list);

    }

    void showErrorMessage(){
        Log.d("MainActivity", "Error");
    }
}
