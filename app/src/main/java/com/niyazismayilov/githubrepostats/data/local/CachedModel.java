package com.niyazismayilov.githubrepostats.data.local;


import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.niyazismayilov.githubrepostats.utils.Constants;

@Entity(tableName = Constants.TABLE_FAVS)
public class CachedModel {
    @PrimaryKey(autoGenerate = true)
    int id = 0;

    private String description;

    private String name;

    private String stargazers_count;

    private String avatar_url;

    private String language;

    private String forks;

    private String created_at;

    private String html_url;

    private String login;

    public CachedModel(String description, String name, String stargazers_count, String avatar_url, String language, String forks, String created_at, String html_url, String login) {
        this.description = description;
        this.name = name;
        this.stargazers_count = stargazers_count;
        this.avatar_url = avatar_url;
        this.language = language;
        this.forks = forks;
        this.created_at = created_at;
        this.html_url = html_url;
        this.login = login;
    }

    public int getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public String getName() {
        return name;
    }

    public String getStargazers_count() {
        return stargazers_count;
    }

    public String getAvatar_url() {
        return avatar_url;
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

    public String getHtml_url() {
        return html_url;
    }

    public String getLogin() {
        return login;
    }
}
