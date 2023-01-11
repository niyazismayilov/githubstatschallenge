package com.niyazismayilov.githubrepostats.ui.fragment.favlist;

import androidx.lifecycle.MutableLiveData;

import com.niyazismayilov.githubrepostats.data.IDataManager;
import com.niyazismayilov.githubrepostats.data.Resource;
import com.niyazismayilov.githubrepostats.data.model.request.RepoListRequest;
import com.niyazismayilov.githubrepostats.data.model.response.RepoListResponse;
import com.niyazismayilov.githubrepostats.ui.base.BaseViewModel;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class FavoriteListViewModel extends BaseViewModel {
    IDataManager iDataManager;
    public MutableLiveData<Resource<RepoListResponse>> repoListResponseMutableLiveData = new MutableLiveData<>();

    @Inject
    public FavoriteListViewModel(IDataManager iDataManager) {
        this.iDataManager = iDataManager;
    }

    public void getRepoList(RepoListRequest repoListRequest) {
        compositeDisposable.add(
                iDataManager.getApi().getRepoList(repoListRequest.getCreatedAt(), repoListRequest.getSort(), repoListRequest.getOrder(), repoListRequest.getPage())
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(response -> {
                            repoListResponseMutableLiveData.postValue(Resource.success(response));
                        }, throwable -> {
                            repoListResponseMutableLiveData.postValue(Resource.error(throwable));
                        }));
    }
}