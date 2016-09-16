package com.hisao.asyncthread;

import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import java.lang.ref.WeakReference;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final ImageView imageView = (ImageView) findViewById(R.id.img_image);


        AsyncTask <String, Integer, Bitmap> downloadImage = new AsyncTask<String, Integer, Bitmap>(){


            @Override
            protected void onPostExecute(Bitmap bitmap) {
                imageView.setImageBitmap(bitmap);
            }

            @Override
            protected Bitmap doInBackground(String... strings) {
                return DownloadUtil.downloadBitmap(strings[0]);
            }

            @Override
            protected void onProgressUpdate(Integer... values) {
                super.onProgressUpdate(values);
            }
        };

        downloadImage.execute("https://media.licdn.com/mpr/mpr/shrinknp_200_200/p/1/000/00b/22e/1572eca.jpg");

    }


}
