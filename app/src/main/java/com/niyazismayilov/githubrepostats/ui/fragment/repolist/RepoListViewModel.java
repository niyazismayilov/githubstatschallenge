package com.niyazismayilov.githubrepostats.ui.fragment.repolist;

import androidx.lifecycle.MutableLiveData;

import com.niyazismayilov.githubrepostats.data.IDataManager;
import com.niyazismayilov.githubrepostats.data.Resource;
import com.niyazismayilov.githubrepostats.data.local.CachedModel;
import com.niyazismayilov.githubrepostats.data.model.request.RepoListRequest;
import com.niyazismayilov.githubrepostats.data.model.response.RepoItem;
import com.niyazismayilov.githubrepostats.ui.base.BaseViewModel;
import com.niyazismayilov.githubrepostats.utils.Constants;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;

import io.reactivex.schedulers.Schedulers;

public class RepoListViewModel extends BaseViewModel {
    IDataManager iDataManager;
    public MutableLiveData<Resource<List<RepoItem>>> repoListResponseMutableLiveData = new MutableLiveData<>();
    public List<RepoItem> cachedList = new ArrayList<>();
    public List<RepoItem> searchList = new ArrayList<>();
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
                            cachedList.addAll(response.getRepoItemList());
                            searchList.addAll(response.getRepoItemList());
                        }, throwable -> repoListResponseMutableLiveData.postValue(Resource.error(throwable))));
    }


    public void filterByQuery(String text) {
        List<RepoItem> list = new ArrayList<>();
        for (RepoItem repoItem : searchList) {
            if (repoItem.getName().contains(text)) {
                list.add(repoItem);
            }
        }
        cachedList.clear();
        cachedList.addAll(list);
        repoListResponseMutableLiveData.postValue(Resource.success(cachedList));
    }

    public void addToFavorites(RepoItem item) {
        compositeDisposable.add(
                iDataManager.getCachedRepo().insert(new CachedModel(item.getDescription(), item.getName(), item.getStars(), item.getRepoOwner().getAvatar_url(),item.getLanguage(),item.getForks(),item.getCreated_at(),item.getRepoOwner().getHtml_url(),item.getRepoOwner().getLogin()))
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe()
        );
    }

    public String getLastDateTime(String type) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Calendar cal = Calendar.getInstance();
        switch (type) {
            case Constants.DAY:
                cal.add(Calendar.DATE, -1);
                break;
            case Constants.MONTHLY:
                cal.add(Calendar.MONTH, -1);
                break;
            case Constants.WEEKLY:
                cal.add(Calendar.DAY_OF_WEEK, -1);
                break;
        }
        return new StringBuilder().append("created:").append(format.format(cal.getTime())).toString();
    }

    public void clearList() {
        cachedList.clear();
    }
}