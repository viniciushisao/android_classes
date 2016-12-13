package br.com.hisao.service;

import android.app.IntentService;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by vinicius on 9/18/16.
 */
public class UsingIntentService extends IntentService {

    /**
     * A constructor is required, and must call the super IntentService(String)
     * constructor with a name for the worker thread.
     */
    public UsingIntentService() {
        super("UsingIntentService");
        Log.d("Vinicius", "IntentService started");
    }

    /**
     * The IntentService calls this method from the default worker thread with
     * the intent that started the service. When this method returns, IntentService
     * stops the service, as appropriate.
     */
    @Override
    protected void onHandleIntent(Intent intent) {

        // Normally we would do some work here, like download a file.
        // For our sample, we just sleep for 5 seconds.

        int ammountSeconds = 15;
        int contSeconts = 0;

        while (ammountSeconds > contSeconts) {
            synchronized (this) {
                try {
                    Log.d("Vinicius", "running IntentService: " + contSeconts++);
                    wait(1000);
                } catch (Exception e) {
                }
            }
        }
        Log.d("Vinicius", "IntentService finished");
    }
}