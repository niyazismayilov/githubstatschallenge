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
    @SerializedName("stargazers_count")
    private String stargazers_count;

    @Expose
    @SerializedName("language")
    private String language;

    @Expose
    @SerializedName("forks")
    private String forks;

    @Expose
    @SerializedName("created_at")
    private String created_at;


    @Expose
    @SerializedName("owner")
    private RepoOwner repoOwner;


    public String getDescription() {
        return description;
    }

    public String getName() {
        return name;
    }

    public String getStars() {
        return stargazers_count;
    }

    public int getId() {
        return id;
    }

    public RepoOwner getRepoOwner() {
        return repoOwner;
    }

    public RepoItem(int id, String description, String name, String stargazers_count, String language, String forks, String created_at, RepoOwner repoOwner) {
        this.id = id;
        this.description = description;
        this.name = name;
        this.stargazers_count = stargazers_count;
        this.language = language;
        this.forks = forks;
        this.created_at = created_at;
        this.repoOwner = repoOwner;
    }

    public String getLanguage() {
        return language;
    }

    public String getForks() {
        return forks;
    }

    public String getCreated_at() {
        return created_at;
    }
}
