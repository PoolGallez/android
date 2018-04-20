package com.example.genji.am311_conductor;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;

import com.bluelinelabs.conductor.Conductor;
import com.bluelinelabs.conductor.Router;
import com.bluelinelabs.conductor.RouterTransaction;
import com.bluelinelabs.conductor.changehandler.FadeChangeHandler;

public class MainActivity extends AppCompatActivity {

    private Router router;
    private static int k;
    private String[] textResources; // do not initialize here
    private int[] colorResources; // do not initialize here

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textResources = new String[]{
                getString(R.string.text1),
                getString(R.string.text2)
        };
        colorResources = new int[]{
                getResources().getColor(R.color.green),
                getResources().getColor(R.color.yellow),
                getResources().getColor(R.color.orange),
                getResources().getColor(R.color.light_blue),
                getResources().getColor(R.color.pink)
        };

        ViewGroup container = findViewById(R.id.controller_container);

        router = Conductor.attachRouter(this, container, savedInstanceState);
        if (!router.hasRootController()) {
            // create bundle for MyController
            Bundle bundle = new Bundle();
            String header = getString(R.string.controller_header);
            bundle.putString("header", header);
            bundle.putString("text", textResources[0]);
            bundle.putInt("color", colorResources[0]);
            router.setRoot(RouterTransaction.with(MyController.get(bundle)));
        }
    }

    @Override
    public void onBackPressed() {
        if (!router.handleBack()) {
            super.onBackPressed();
        }
    }

    public void changeController(View v){
        k++;
        String header = getString(R.string.controller_header);
        Bundle bundle = new Bundle();
        bundle.putString("header", header);
        bundle.putString("text", textResources[k%2]);
        bundle.putInt("color", colorResources[k%5]);
        router.pushController(RouterTransaction
                .with(MyController.get(bundle))
                .pushChangeHandler(new FadeChangeHandler())
                .popChangeHandler(new FadeChangeHandler()));

    }
}
