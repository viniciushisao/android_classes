package br.com.hisao.json;

/**
 * Created by vinicius on 9/24/16.
 */

        import android.app.Activity;
        import android.graphics.Bitmap;
        import android.os.AsyncTask;
        import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
        import android.widget.ImageView;
        import android.widget.TextView;

import org.json.JSONArray;
        import org.json.JSONException;
        import org.json.JSONObject;
        import org.w3c.dom.Text;

/**
 * Created by viniciushisao
 */
public class GithubUsersAdapter extends BaseAdapter{

    private JSONArray jsonArray;
    private Activity activity;

    public GithubUsersAdapter(JSONArray jsonArray, Activity activity){
        this.jsonArray = jsonArray;
        this.activity = activity;
    }

    @Override
    public int getCount() {
        return jsonArray.length();
    }

    @Override
    public Object getItem(int i) {
        try {
            return jsonArray.get(i);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        LayoutInflater inflater = (activity).getLayoutInflater();
        View row = inflater.inflate(R.layout.githubuser_item, viewGroup, false);

        TextView txvProject = (TextView) row.findViewById(R.id.txv_project);
        final ImageView imvAvatar = (ImageView) row.findViewById(R.id.imv_avatar);
        JSONObject jsonObject;
        String avatarUrl = null;
        try {
            jsonObject = this.jsonArray.getJSONObject(i);
            txvProject.setText(jsonObject.getString("name"));
            avatarUrl = jsonObject.getJSONObject("owner").getString("avatar_url");
        } catch (JSONException e) {
            e.printStackTrace();
        }

        AsyncTask<String, Integer, Bitmap> downloadImage = new AsyncTask<String, Integer, Bitmap>(){


            @Override
            protected void onPostExecute(Bitmap bitmap) {
                imvAvatar.setImageBitmap(bitmap);
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

        downloadImage.execute(avatarUrl);

        return row;
    }
}

