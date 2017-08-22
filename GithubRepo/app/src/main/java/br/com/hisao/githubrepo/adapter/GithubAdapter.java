package br.com.hisao.githubrepo.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import br.com.hisao.githubrepo.R;
import br.com.hisao.githubrepo.model.Repo;
import br.com.hisao.githubrepo.util.Log;

/**
 * Created by vinicius on 19/08/17.
 */

public class GithubAdapter extends RecyclerView.Adapter<GithubAdapter.ViewHolder> {


    private List<Repo> mRepoList;

    public GithubAdapter(List<Repo> repoList) {
        this.mRepoList = repoList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View contactView = inflater.inflate(R.layout.githubrepo_list_item, parent, false);
        return new ViewHolder(contactView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Repo repo = mRepoList.get(position);

        if (repo != null) {

            holder.txvId.setText(repo.getId().toString());
            holder.txvName.setText(repo.getName());
            holder.txvDescription.setText(repo.getDescription());

        } else {
            Log.e("GithubAdapter:onBindViewHolder:49 ");
        }
    }

    @Override
    public int getItemCount() {
        return mRepoList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView txvName;
        TextView txvId;
        TextView txvDescription;

        public ViewHolder(View itemView) {
            super(itemView);
            txvDescription = itemView.findViewById(R.id.txvDescription);
            txvId = itemView.findViewById(R.id.txvId);
            txvName = itemView.findViewById(R.id.txvName);
        }
    }
}
