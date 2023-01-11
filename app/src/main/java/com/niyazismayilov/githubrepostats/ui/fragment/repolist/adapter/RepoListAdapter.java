package com.niyazismayilov.githubrepostats.ui.fragment.repolist.adapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.niyazismayilov.githubrepostats.R;
import com.niyazismayilov.githubrepostats.data.model.response.RepoItem;
import java.util.List;

public class RepoListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private final int VIEW_TYPE_ITEM = 0;
    private final int VIEW_TYPE_LOADING = 1;
    private final OnItemClickListener listener;
    public List<RepoItem> mItemList;


    public RepoListAdapter(List<RepoItem> itemList,OnItemClickListener listener) {
        mItemList = itemList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == VIEW_TYPE_ITEM) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.repo_rv_item, parent, false);

            return new ItemViewHolder(view);
        } else {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.repo_rv_item_loading, parent, false);
            return new LoadingViewHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int position) {

        if (viewHolder instanceof ItemViewHolder) {
            viewHolder.itemView.setOnClickListener(v -> listener.onItemClick(mItemList.get(position)));
            populateItemRows((ItemViewHolder) viewHolder, position);
        } else if (viewHolder instanceof LoadingViewHolder) {
            showLoadingView((LoadingViewHolder) viewHolder, position);
        }

    }

    @Override
    public int getItemCount() {
        return mItemList == null ? 0 : mItemList.size();
    }

    @Override
    public int getItemViewType(int position) {
        return mItemList.get(position) == null ? VIEW_TYPE_LOADING : VIEW_TYPE_ITEM;
    }


    private class ItemViewHolder extends RecyclerView.ViewHolder {

        TextView tvItem;
        ImageView ivAvatar;

        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);

            tvItem = itemView.findViewById(R.id.tv_name);
            ivAvatar = itemView.findViewById(R.id.iv_avatar);

        }
    }

    private class LoadingViewHolder extends RecyclerView.ViewHolder {

        ProgressBar progressBar;

        public LoadingViewHolder(@NonNull View itemView) {
            super(itemView);
            progressBar = itemView.findViewById(R.id.progressBar);
        }
    }

    private void showLoadingView(LoadingViewHolder viewHolder, int position) {

    }

    private void populateItemRows(ItemViewHolder viewHolder, int position) {

        RepoItem item = mItemList.get(position);
        viewHolder.tvItem.setText(item.getName());
        Glide.with(viewHolder.itemView)
                .load(item.getRepoOwner().getAvatar_url())
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(viewHolder.ivAvatar);

    }
    public interface OnItemClickListener {
        void onItemClick(RepoItem item);
    }


}
