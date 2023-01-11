package com.niyazismayilov.githubrepostats.data.model.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class RepoListResponse {
    @Expose
    @SerializedName("total_count")
    private int totalCount;

    @Expose
    @SerializedName("items")
    private List<RepoItem> repoItemList;

    public int getTotalCount() {
        return totalCount;
    }

    public List<RepoItem> getRepoItemList() {
        return repoItemList;
    }
}

class RepoOwner {

    @Expose
    @SerializedName("avatar_url")
    private String avatar_url;

    @Expose
    @SerializedName("stargazers_count")
    private String stargazers_count;



}