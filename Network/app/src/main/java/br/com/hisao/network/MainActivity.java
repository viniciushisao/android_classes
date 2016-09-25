package br.com.hisao.network;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Exercice: get https://api.github.com/users/viniciushisao/repos and print the img and login
 */

public class MainActivity extends AppCompatActivity {

    private TextView txvAnswer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txvAnswer = (TextView) findViewById(R.id.txv_answer);

        //Http client was deprecated in 22 and removed on 23

        Button btnGet = (Button) findViewById(R.id.btn_get);
        btnGet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AsyncTask.execute(new Runnable() {
                    @Override
                    public void run() {
                        URL url;
                        HttpURLConnection urlConnection;
                        try {
                            url = new URL("https://httpbin.org/get");
                            urlConnection = (HttpURLConnection) url.openConnection();
                            urlConnection.setRequestMethod("GET");
                            int responseCode = urlConnection.getResponseCode();
                            if (responseCode == HttpURLConnection.HTTP_OK) {
                                String server_response = readStream(urlConnection.getInputStream());
                                JSONObject obj = new JSONObject(server_response);
                                setText(obj.toString());
                            }
                        } catch (MalformedURLException e) {
                            e.printStackTrace();
                        } catch (IOException e) {
                            e.printStackTrace();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });
            }
        });

        Button btnPost = (Button) findViewById(R.id.btn_post);
        btnPost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AsyncTask.execute(new Runnable() {
                    @Override
                    public void run() {
                        URL url;
                        HttpURLConnection urlConnection;
                        try {
                            url = new URL("https://httpbin.org/post");
                            urlConnection = (HttpURLConnection) url.openConnection();
                            urlConnection.setRequestMethod("POST");
                            //urlConnection.setRequestProperty("Content-Type", "application/json");
                            //urlConnection.setRequestProperty("Accept", "application/json");
                            int responseCode = urlConnection.getResponseCode();
                            if (responseCode == HttpURLConnection.HTTP_OK) {
                                String server_response = readStream(urlConnection.getInputStream());
                                JSONObject obj = new JSONObject(server_response);
                                setText(obj.toString());
                            }
                        } catch (MalformedURLException e) {
                            e.printStackTrace();
                        } catch (IOException e) {
                            e.printStackTrace();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });
            }
        });


        Button btnPut = (Button) findViewById(R.id.btn_put);
        btnPut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AsyncTask.execute(new Runnable() {
                    @Override
                    public void run() {
                        URL url;
                        HttpURLConnection urlConnection;
                        try {
                            url = new URL("https://httpbin.org/put");
                            urlConnection = (HttpURLConnection) url.openConnection();
                            urlConnection.setRequestMethod("PUT");
                            //urlConnection.setRequestProperty("Content-Type", "application/json");
                            //urlConnection.setRequestProperty("Accept", "application/json");
                            int responseCode = urlConnection.getResponseCode();
                            if (responseCode == HttpURLConnection.HTTP_OK) {
                                String server_response = readStream(urlConnection.getInputStream());
                                JSONObject obj = new JSONObject(server_response);
                                setText(obj.toString());
                            }
                        } catch (MalformedURLException e) {
                            e.printStackTrace();
                        } catch (IOException e) {
                            e.printStackTrace();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });
            }
        });
    }


    public void setText(final String str) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                txvAnswer.setText(str);
            }
        });
    }


    private String readStream(InputStream in) {
        BufferedReader reader = null;
        StringBuffer response = new StringBuffer();
        try {
            reader = new BufferedReader(new InputStreamReader(in));
            String line;
            while ((line = reader.readLine()) != null) {
                response.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return response.toString();
    }
}
