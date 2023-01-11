package com.niyazismayilov.githubrepostats.data.api;

import com.niyazismayilov.githubrepostats.data.model.response.RepoListResponse;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface IApi {
    @GET("search/repositories")
    Single<RepoListResponse> getRepoList(@Query("q") String createdAt, @Query("sort") String sort, @Query("order") String order, @Query("page") int page);
}
