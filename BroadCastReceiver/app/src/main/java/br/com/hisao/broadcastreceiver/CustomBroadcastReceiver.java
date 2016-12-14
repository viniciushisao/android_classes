package br.com.hisao.broadcastreceiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

/**
 * Created by viniciushisao
 */

public class CustomBroadcastReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Log.d("CustomBroadcastReceiver", "CustomBroadcastReceiver:onReceive:14 ");
    }
}
