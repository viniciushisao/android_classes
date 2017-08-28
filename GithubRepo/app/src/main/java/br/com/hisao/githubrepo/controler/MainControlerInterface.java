package br.com.hisao.githubrepo.controler;

import java.util.List;

import br.com.hisao.githubrepo.model.Repo;

/**
 * Created by vinicius on 28/08/17.
 */

public interface MainControlerInterface {

    void onDataReceived(List<Repo> repoList);

    void onDataReceivedError();

}