package com.example.itribluehealth;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.IntentFilter;
import android.os.Bundle;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import android.Manifest;
import android.bluetooth.BluetoothAdapter;
import android.content.ContentValues;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;

import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.ListView;
import android.widget.Toast;


import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    private ListView lv;
    BluetoothAdapter blueadapter = BluetoothAdapter.getDefaultAdapter();
    ArrayList AL = new ArrayList();

    private SQL SQL;

    private TextView output;

    public MainActivity() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        BottomNavigationView navView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications, R.id.navigation_setting)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(navView, navController);

        lv = (ListView)findViewById(R.id.listview1);

    }

    public void bt2(View view) {
        AL.clear();
        if (blueadapter.isDiscovering()) {
            //判斷藍牙是否正在掃描，如果是調用取消掃描方法；如果不是，則開始掃描
            blueadapter.cancelDiscovery();
        } else
            blueadapter.startDiscovery();
        IntentFilter intentFilter = new IntentFilter(BluetoothDevice.ACTION_FOUND);//注冊廣播接收信號
        registerReceiver(bluetoothReceiver, intentFilter);//用BroadcastReceiver 來取得結果
        // output.setText("");

    }

    private final BroadcastReceiver bluetoothReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();

            if (BluetoothDevice.ACTION_FOUND.equals(action)) {
                BluetoothDevice device = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
                AL.add(device.getName()+":"+device.getAddress());


            }
        }
    };

    public void bt3(View view) {
        String str= "";
        blueadapter.cancelDiscovery();//關閉掃描
        for (int i = 0 ; i < AL.size();i++){
            str += AL.get(i)+"\n";

        }
        // output.setText(str);
        final ArrayAdapter adapter = new ArrayAdapter
                (this,android.R.layout.simple_list_item_1, AL);
        lv.setAdapter(adapter);

    }
}