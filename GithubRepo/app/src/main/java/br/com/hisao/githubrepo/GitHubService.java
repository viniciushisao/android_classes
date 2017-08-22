package br.com.hisao.githubrepo;

import java.util.List;

import br.com.hisao.githubrepo.model.Repo;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;


/**
 * Created by vinicius on 19/08/17.
 */


public interface GitHubService {

    /**
     * Leave it generic. You might use it one day.
     */

    @GET("users/{user}/repos?")
    Call<List<Repo>> listRepos(@Path("user") String user, @Query("per_page") int per_page, @Query("page") int page);

}
