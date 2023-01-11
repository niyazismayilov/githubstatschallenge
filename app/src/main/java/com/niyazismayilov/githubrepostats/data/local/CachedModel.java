package com.niyazismayilov.githubrepostats.data.local;


import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;

import com.niyazismayilov.githubrepostats.utils.Constants;

@Entity(tableName = Constants.TABLE_FAVS)
public class CachedModel {
    @PrimaryKey(autoGenerate = true)
    int id = 0;

    private String description;

    private String name;

    private String stargazers_url;

    private String avatar_url;

    public CachedModel(String description, String name, String stargazers_url, String avatar_url) {
        this.description = description;
        this.name = name;
        this.stargazers_url = stargazers_url;
        this.avatar_url = avatar_url;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStargazers_url() {
        return stargazers_url;
    }

    public void setStargazers_url(String stargazers_url) {
        this.stargazers_url = stargazers_url;
    }

    public String getAvatar_url() {
        return avatar_url;
    }

    public void setAvatar_url(String avatar_url) {
        this.avatar_url = avatar_url;
    }
}
