package com.niyazismayilov.githubrepostats.ui.fragment.favlist;

import android.os.Build;

import androidx.annotation.RequiresApi;
import androidx.lifecycle.MutableLiveData;

import com.niyazismayilov.githubrepostats.data.IDataManager;
import com.niyazismayilov.githubrepostats.data.Resource;
import com.niyazismayilov.githubrepostats.data.local.CachedModel;
import com.niyazismayilov.githubrepostats.data.model.request.RepoListRequest;
import com.niyazismayilov.githubrepostats.data.model.response.RepoItem;
import com.niyazismayilov.githubrepostats.data.model.response.RepoListResponse;
import com.niyazismayilov.githubrepostats.data.model.response.RepoOwner;
import com.niyazismayilov.githubrepostats.ui.base.BaseViewModel;

import java.util.List;
import java.util.stream.Collectors;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class FavoriteListViewModel extends BaseViewModel {
    IDataManager iDataManager;
    List<CachedModel> cachedModelList;
    public MutableLiveData<Resource<List<RepoItem>>> repoListResponseMutableLiveData = new MutableLiveData<>();

    @Inject
    public FavoriteListViewModel(IDataManager iDataManager) {
        this.iDataManager = iDataManager;
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public void getFavList() {
        compositeDisposable.add(
                iDataManager.getCachedRepo().getAllData()
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(response -> {
                            cachedModelList = response;
                            repoListResponseMutableLiveData.postValue(Resource.success(convertToDao(response)));
                        }, throwable -> {
                            repoListResponseMutableLiveData.postValue(Resource.error(throwable));
                        }));
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private List<RepoItem> convertToDao(List<CachedModel> response) {
       return response.stream().map(item -> new RepoItem(item.getId(),item.getAvatar_url(),item.getDescription(),item.getName(),item.getStargazers_url(),new RepoOwner(item.getAvatar_url(),item.getStargazers_url()))).collect(Collectors.toList());
    }

    public void removeFav(RepoItem model) {
        compositeDisposable.add(
                iDataManager.getCachedRepo().delete(getCachedModel(model))
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe()
        );
    }

    private CachedModel getCachedModel(RepoItem repoItem) {
        for(CachedModel item : cachedModelList) {
            if(item.getId() == repoItem.getId()) return  item;
        }
        return null;
    }
}