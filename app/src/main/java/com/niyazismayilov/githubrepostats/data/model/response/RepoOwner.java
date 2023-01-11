package com.niyazismayilov.githubrepostats.data.model.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RepoOwner {

    @Expose
    @SerializedName("avatar_url")
    private String avatar_url;

    @Expose
    @SerializedName("html_url")
    private String html_url;

    @Expose
    @SerializedName("login")
    private String login;


    public RepoOwner(String avatar_url, String login,String html_url) {
        this.avatar_url = avatar_url;
        this.login = login;
        this.html_url = html_url;
    }

    public String getAvatar_url() {
        return avatar_url;
    }

    public String getLogin() {
        return login;
    }

    public String getHtml_url() {
        return html_url;
    }
}
