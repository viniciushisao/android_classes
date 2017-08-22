package br.com.hisao.githubrepo;

import android.app.Application;

import br.com.hisao.githubrepo.util.Log;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by vinicius on 21/08/17.
 */

public class MyApplication extends Application {

    private static Retrofit mRetrofit;

    @Override
    public void onCreate() {
        super.onCreate();

        Log.d("MyApplication:onCreate:20 ");
        mRetrofit = new Retrofit.Builder()
                .baseUrl("https://api.github.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public static Retrofit getRetrofitInstance() {
        return mRetrofit;
    }

}
