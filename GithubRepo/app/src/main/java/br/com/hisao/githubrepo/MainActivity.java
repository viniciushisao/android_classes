package br.com.hisao.githubrepo;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;

import java.io.IOException;
import java.util.List;

import br.com.hisao.githubrepo.adapter.GithubAdapter;
import br.com.hisao.githubrepo.model.Repo;
import br.com.hisao.githubrepo.util.Log;
import retrofit2.Call;

public class MainActivity extends AppCompatActivity {


    private static final int REPOS_PER_PAGE = 15;
    private static final String REPO_USER = "JakeWharton";

    private RecyclerView rcvContacts;
    private RelativeLayout rllLoading;
    private RelativeLayout rllError;
    private Button btnTryAgain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rcvContacts = (RecyclerView) findViewById(R.id.rcvList);
        rllLoading = (RelativeLayout) findViewById(R.id.rllLoading);
        rllError = (RelativeLayout) findViewById(R.id.rllError);
        btnTryAgain = (Button) findViewById(R.id.btnTryAgain);
        rcvContacts.setHasFixedSize(true);

        btnTryAgain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                hideErrorPage();
                retrieveDataFromInternet();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        retrieveDataFromInternet();
    }

    private void showList(List<Repo> repoList) {
        Log.d("MainActivity:showList:62 ");
        GithubAdapter adapter = new GithubAdapter(repoList);

        rcvContacts.setLayoutManager(new LinearLayoutManager(this));
        rcvContacts.setAdapter(adapter);

    }

    private void showLoadingPage() {
        rllLoading.setVisibility(View.VISIBLE);
    }

    private void hideLoadingPage() {
        rllLoading.setVisibility(View.GONE);
    }

    private void showErrorPage(){
        rllError.setVisibility(View.VISIBLE);
    }

    private void hideErrorPage(){
        rllError.setVisibility(View.GONE);
    }

    private void retrieveDataFromInternet() {
        showLoadingPage();
        AsyncTask<Call<List<Repo>>, Void, List<Repo>> myTask = new AsyncTask<Call<List<Repo>>, Void, List<Repo>>() {

            @Override
            protected List<Repo> doInBackground(Call<List<Repo>>... listCall) {
                List<Repo> repoList = null;
                try {
                    repoList = listCall[0].execute().body();
                } catch (IOException e) {
                    Log.d("MainActivity:doInBackground:50 " + e.getMessage());
                }
                return repoList;
            }

            @Override
            protected void onPostExecute(List<Repo> repoList) {
                super.onPostExecute(repoList);
                if (repoList != null) {
                    hideLoadingPage();
                    showList(repoList);
                }else{
                    showErrorPage();
                }
            }
        };

        GitHubService service = MyApplication.getRetrofitInstance().create(GitHubService.class);
        Call<List<Repo>> repos = service.listRepos(REPO_USER, REPOS_PER_PAGE, 1);
        myTask.execute(repos, null, null);

    }
}
