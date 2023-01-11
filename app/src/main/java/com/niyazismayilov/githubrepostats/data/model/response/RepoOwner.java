package com.niyazismayilov.githubrepostats.data.model.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RepoOwner {

    @Expose
    @SerializedName("avatar_url")
    private String avatar_url;

    @Expose
    @SerializedName("stargazers_count")
    private String stargazers_count;


    public RepoOwner(String avatar_url, String stargazers_count) {
        this.avatar_url = avatar_url;
        this.stargazers_count = stargazers_count;
    }

    public String getAvatar_url() {
        return avatar_url;
    }

    public String getStargazers_count() {
        return stargazers_count;
    }
}
