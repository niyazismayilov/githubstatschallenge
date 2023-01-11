package com.niyazismayilov.githubrepostats.ui.fragment.repolist;

import android.os.Build;

import androidx.annotation.RequiresApi;
import androidx.lifecycle.MutableLiveData;

import com.niyazismayilov.githubrepostats.data.IDataManager;
import com.niyazismayilov.githubrepostats.data.Resource;
import com.niyazismayilov.githubrepostats.data.model.request.RepoListRequest;
import com.niyazismayilov.githubrepostats.data.model.response.RepoItem;
import com.niyazismayilov.githubrepostats.data.model.response.RepoListResponse;
import com.niyazismayilov.githubrepostats.ui.base.BaseViewModel;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;

import io.reactivex.schedulers.Schedulers;

public class RepoListViewModel extends BaseViewModel {
    IDataManager iDataManager;
    public MutableLiveData<Resource<List<RepoItem>>> repoListResponseMutableLiveData = new MutableLiveData<>();
    List<RepoItem> cachedList;

    @Inject
    public RepoListViewModel(IDataManager iDataManager) {
        this.iDataManager = iDataManager;
    }

    public void getRepoList(RepoListRequest repoListRequest) {
        repoListResponseMutableLiveData.postValue(Resource.loading());
        compositeDisposable.add(
                iDataManager.getApi().getRepoList(repoListRequest.getCreatedAt(), repoListRequest.getSort(), repoListRequest.getOrder(), repoListRequest.getPage())
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(response -> {
                            repoListResponseMutableLiveData.postValue(Resource.success(response.getRepoItemList()));
                            cachedList = response.getRepoItemList();
                        }, throwable -> repoListResponseMutableLiveData.postValue(Resource.error(throwable))));
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public void filterByQuery(String text) {
        Predicate<RepoItem> byName = item -> item.getName().contains(text);
        repoListResponseMutableLiveData.postValue(Resource.success(cachedList.stream().filter(byName)
                .collect(Collectors.toList())));
    }
}