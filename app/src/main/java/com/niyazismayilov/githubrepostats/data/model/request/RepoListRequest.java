package com.niyazismayilov.githubrepostats.data.model.request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RepoListRequest {
    @Expose
    @SerializedName("q")
    private String createdAt;

    @Expose
    @SerializedName("sort")
    private String sort;

    @Expose
    @SerializedName("order")
    private String order;

    @Expose
    @SerializedName("page")
    private int page;

    public RepoListRequest(String createdAt, String sort, String order, int page) {
        this.createdAt = createdAt;
        this.sort = sort;
        this.order = order;
        this.page = page;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public String getSort() {
        return sort;
    }

    public String getOrder() {
        return order;
    }

    public int getPage() {
        return page;
    }
}
