package com.niyazismayilov.githubrepostats.ui.fragment.favlist;

import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;

import com.niyazismayilov.githubrepostats.BR;
import com.niyazismayilov.githubrepostats.R;
import com.niyazismayilov.githubrepostats.data.Resource;
import com.niyazismayilov.githubrepostats.data.model.request.RepoListRequest;
import com.niyazismayilov.githubrepostats.databinding.FragmentFavListBinding;
import com.niyazismayilov.githubrepostats.databinding.FragmentRepoListBinding;
import com.niyazismayilov.githubrepostats.di.component.FragmentComponent;
import com.niyazismayilov.githubrepostats.ui.base.BaseFragment;
import com.niyazismayilov.githubrepostats.ui.fragment.repolist.RepoListViewModel;
import com.niyazismayilov.githubrepostats.ui.fragment.repolist.adapter.RepoListAdapter;
import com.niyazismayilov.githubrepostats.utils.Constants;

public class FavoriteListFragment extends BaseFragment<FragmentFavListBinding, FavoriteListViewModel>  {

    @Override
    public int getBindingVariable() {
        return BR.viewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_fav_list;
    }

    @Override
    public void performDependencyInjection(FragmentComponent buildComponent) {
        buildComponent.inject(this);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mViewModel.getFavList();
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setUpObservers();

    }

    private void setUpObservers() {
        mViewModel.repoListResponseMutableLiveData.observe(getViewLifecycleOwner(), result -> {
            switch (result.getType()) {
                case Resource.SUCCESS:
                   getViewDataBinding().reposListRecycler.setAdapter(new RepoListAdapter(result.getData(), item -> mViewModel.removeFav(item)));
                    break;
                case Resource.ERROR:
                    break;
                case Resource.LOADING:
                    break;
            }
        });
    }

}
