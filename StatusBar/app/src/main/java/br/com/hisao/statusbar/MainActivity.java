package br.com.hisao.statusbar;

import android.app.AlarmManager;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.NotificationCompat;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    NotificationCompat.Builder notification;
    PendingIntent pIntent;
    NotificationManager manager;
    Intent resultIntent;
    TaskStackBuilder stackBuilder;
    private PendingIntent pendingIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnSimpleNotification = (Button) findViewById(R.id.btn_simplenotification);
        btnSimpleNotification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Creating Notification Builder
                notification = new NotificationCompat.Builder(MainActivity.this);
                //Title for Notification
                notification.setContentTitle("Notification title");
                //Message in the Notification
                notification.setContentText("New Post on Android Notification.");
                //Alert shown when Notification is received
                notification.setTicker("New Message Alert!");
                //Icon to be set on Notification
                notification.setSmallIcon(R.drawable.icon_king);
                //Creating new Stack Builder
                stackBuilder = TaskStackBuilder.create(MainActivity.this);
                stackBuilder.addParentStack(Result.class);
                //Intent which is opened when notification is clicked
                resultIntent = new Intent(MainActivity.this, Result.class);
                stackBuilder.addNextIntent(resultIntent);
                pIntent =  stackBuilder.getPendingIntent(0,PendingIntent.FLAG_UPDATE_CURRENT);
                notification.setContentIntent(pIntent);
                manager =(NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
                manager.notify(0, notification.build());

            }
        });

        final Button btnAlarmBroadcastReceiver = (Button) findViewById(R.id.btn_alarmbroadcastreceiver);
        btnAlarmBroadcastReceiver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                    BroadcastReceiver receiver = new BroadcastReceiver() {
                        @Override public void onReceive( Context context, Intent _ )
                        {
                            btnAlarmBroadcastReceiver.setBackgroundColor( Color.RED );
                            context.unregisterReceiver( this ); // this == BroadcastReceiver, not Activity
                        }
                    };

                    MainActivity.this.registerReceiver( receiver, new IntentFilter("com.blah.blah.somemessage") );

                    PendingIntent pintent = PendingIntent.getBroadcast( MainActivity.this, 0, new Intent("com.blah.blah.somemessage"), 0 );
                    AlarmManager manager = (AlarmManager)(MainActivity.this.getSystemService( Context.ALARM_SERVICE ));

                    // set alarm to fire 5 sec (1000*5) from now (SystemClock.elapsedRealtime())
                    manager.set( AlarmManager.ELAPSED_REALTIME_WAKEUP, SystemClock.elapsedRealtime() + 1000*5, pintent );
                }
        });

    }
}
