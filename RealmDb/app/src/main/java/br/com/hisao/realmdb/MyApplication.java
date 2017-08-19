package br.com.hisao.realmdb;

import android.app.Application;

import io.realm.Realm;

/**
 * Created by vinicius on 19/08/17.
 */

public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        Realm.init(this);
    }
}
