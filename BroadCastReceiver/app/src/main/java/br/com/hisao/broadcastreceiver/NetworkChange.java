package br.com.hisao.broadcastreceiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

/**
 * Created by viniciushisao
 */

public class NetworkChange extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {

        Log.d("NetworkChange", "NetworkChange:onReceive");

        if (isOnline(context)) {
            Log.d("NetworkChange", "NetworkChange:onReceive: YES INTERNET");
        }else{
            Log.d("NetworkChange", "NetworkChange:onReceive: NO INTERNET");
        }
    }

    public boolean isOnline(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        return (netInfo != null && netInfo.isConnected());
    }
}
