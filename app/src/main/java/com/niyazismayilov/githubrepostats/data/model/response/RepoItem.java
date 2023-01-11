package com.niyazismayilov.githubrepostats.data.model.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RepoItem {

    @Expose
    @SerializedName("description")
    private String description;

    @Expose
    @SerializedName("name")
    private String name;

    @Expose
    @SerializedName("stargazers_url")
    private String stargazers_url;


    @Expose
    @SerializedName("owner")
    private RepoOwner repoOwner;


    public String getDescription() {
        return description;
    }

    public String getName() {
        return name;
    }

    public String getStargazers_url() {
        return stargazers_url;
    }

    public RepoOwner getRepoOwner() {
        return repoOwner;
    }
}
