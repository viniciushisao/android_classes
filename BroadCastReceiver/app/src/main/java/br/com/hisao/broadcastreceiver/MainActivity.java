package br.com.hisao.broadcastreceiver;

import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;



public class MainActivity extends AppCompatActivity {

    public static final String CUSTOM_BROADCAST = "br.com.hisao.android.CUSTOM_BROADCAST";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        registerReceiver(new CustomBroadcastReceiver(),new IntentFilter(CUSTOM_BROADCAST));

        Button btnLaunchBroadcastReceiver = (Button) findViewById(R.id.btn_launch_broadcastreceiver);
        btnLaunchBroadcastReceiver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CUSTOM_BROADCAST);
                sendBroadcast(intent);
            }
        });
    }
}
