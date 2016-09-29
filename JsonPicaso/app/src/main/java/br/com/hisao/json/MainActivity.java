package br.com.hisao.json;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnStartProcess = (Button) findViewById(R.id.btn_startprocess);
        btnStartProcess.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AsyncTask<Void, Void, JSONArray> taskGetJson = new AsyncTask<Void, Void, JSONArray>() {
                    @Override
                    protected JSONArray doInBackground(Void... params) {
                        return JsonUtil.getJson("https://api.github.com/users/viniciushisao/repos");
                    }

                    @Override
                    protected void onPostExecute(JSONArray jsonArray) {
                        super.onPostExecute(jsonArray);
                        if (BuildConfig.DEBUG){
                            try {
                                for (int i = 0; i < jsonArray.length(); i++) {
                                    JSONObject explrObject;
                                    explrObject = jsonArray.getJSONObject(i);
                                    Log.d("Vinicius", explrObject.getString("name"));
                                    Log.d("Vinicius", explrObject.getJSONObject("owner").getString("avatar_url"));
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            } catch (Exception e){
                                e.printStackTrace();
                            }
                        }

                        ListView lstNumbers =(ListView)findViewById(R.id.lsv_users);
                        lstNumbers.setAdapter(new GithubUsersAdapter(jsonArray, MainActivity.this));
                    }
                };
                taskGetJson.execute(null, null, null);
            }
    }

    );
}

}
