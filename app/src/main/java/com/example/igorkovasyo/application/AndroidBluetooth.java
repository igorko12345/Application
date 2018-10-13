package com.example.igorkovasyo.application;

import android.bluetooth.BluetoothAdapter;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class AndroidBluetooth extends AppCompatActivity {
    private static final int REQUEST_PAIRED_DEVICE = 2;

    Button btnListPairedDevices;
    TextView stateBluetooth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        Button btntOn = findViewById(R.id.btnOn);
        Button btntOff = findViewById(R.id.btnOFF);
        Button btnDisc = findViewById(R.id.btnDiscoverable);
        btnListPairedDevices = findViewById(R.id.listpaireddevices);

        stateBluetooth = findViewById(R.id.bluetoothstate);
        final BluetoothAdapter bAdapter = BluetoothAdapter.getDefaultAdapter();
        btnListPairedDevices.setOnClickListener(btnListPairedDevicesOnClickListener);
        btntOn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(bAdapter == null)
                {
                    Toast.makeText(getApplicationContext(),"Bluetooth Not Supported",Toast.LENGTH_SHORT).show();
                }
                else{
                    if(!bAdapter.isEnabled()){
                        startActivityForResult(new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE),1);
                        Toast.makeText(getApplicationContext(),"Bluetooth Turned ON",Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
        btntOff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bAdapter.disable();
                Toast.makeText(getApplicationContext(),"Bluetooth Turned OFF", Toast.LENGTH_SHORT).show();
            }
        });
        btnDisc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!bAdapter.isDiscovering()){
                    startActivityForResult(new Intent(BluetoothAdapter.ACTION_REQUEST_DISCOVERABLE),1);
                    Toast.makeText(getApplicationContext(),"Making Device Discoverable",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private Button.OnClickListener btnListPairedDevicesOnClickListener
            = new Button.OnClickListener(){

        @Override
        public void onClick(View arg0) {
            Intent intent = new Intent();
            intent.setClass(AndroidBluetooth.this, ListPairedDevicesActivity.class);
            startActivityForResult(intent, REQUEST_PAIRED_DEVICE);
        }};
}