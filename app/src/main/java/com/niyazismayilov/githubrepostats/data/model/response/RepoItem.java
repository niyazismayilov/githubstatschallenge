package com.niyazismayilov.githubrepostats.data.model.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RepoItem {

    int id;
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

    public int getId() {
        return id;
    }

    public RepoOwner getRepoOwner() {
        return repoOwner;
    }

    public RepoItem(int id,String avatar_url, String description, String name, String stargazers_url, RepoOwner repoOwner) {
        this.id= id;
        this.description = description;
        this.name = name;
        this.stargazers_url = stargazers_url;
        this.repoOwner = repoOwner;
    }
}
