package com.example.genji.am106_bluetooth;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

public class MainActivity extends AppCompatActivity {

    private BluetoothAdapter BA;
    private Set<BluetoothDevice> pairedDevices;
    ListView lv;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BA = BluetoothAdapter.getDefaultAdapter();
        lv = (ListView)findViewById(R.id.listView);

    }

    public void onClick(View view){
        int id = view.getId();
        switch(id){
            case(R.id.on):
                if (!BA.isEnabled()) {
                    Intent turnOn = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
                    startActivity(turnOn);
                    Toast.makeText(getApplicationContext(), "Turned on",Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(getApplicationContext(), "Already on", Toast.LENGTH_LONG).show();
                }
                break;
            case(R.id.off):
                BA.disable();
                Toast.makeText(getApplicationContext(), "Turned off" ,Toast.LENGTH_LONG).show();
                break;
            case(R.id.visible):
                Intent getVisible = new Intent(BluetoothAdapter.ACTION_REQUEST_DISCOVERABLE);
                startActivity(getVisible);
                break;
            case(R.id.list):
                pairedDevices = BA.getBondedDevices();

                final List<HashMap<String,String>> listDevice = new ArrayList<>();

                for(BluetoothDevice bt : pairedDevices){
                    String name = bt.getName();
                    String MAC = bt.getAddress();
                    HashMap<String, String> item = new HashMap<>();
                    item.put("name", name);
                    item.put("MAC", MAC);
                    listDevice.add(item);
                }

                SimpleAdapter adapter = new SimpleAdapter(this, listDevice, android.R.layout.simple_list_item_2,
                        new String[] {"name", "MAC"},
                        new int[] {android.R.id.text1, android.R.id.text2});
                lv.setAdapter(adapter);

                Toast.makeText(getApplicationContext(), "Showing Paired Devices",Toast.LENGTH_SHORT).show();

                lv.setAdapter(adapter);
                break;
        }
    }
}
