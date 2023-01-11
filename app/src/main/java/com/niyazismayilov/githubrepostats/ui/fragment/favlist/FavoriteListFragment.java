package com.niyazismayilov.githubrepostats.ui.fragment.favlist;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import androidx.annotation.NonNull;

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

public class FavoriteListFragment extends BaseFragment<FragmentFavListBinding, FavoriteListViewModel> implements AdapterView.OnItemSelectedListener {
    private String[] sorting = {Constants.WEEKLY, Constants.MONTHLY, Constants.YEARLY};

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

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mViewModel.getRepoList(new RepoListRequest("created:2017-05-20", "stars", "desc", 1));
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setUpObservers();
        setUpSpinner();

    }

    private void setUpObservers() {
        mViewModel.repoListResponseMutableLiveData.observe(getViewLifecycleOwner(), result -> {
            switch (result.getType()) {
                case Resource.SUCCESS:
//                    getViewDataBinding().reposListRecycler.setAdapter(new RepoListAdapter(result.getData().getRepoItemList()));
                    break;
                case Resource.ERROR:
                    break;
                case Resource.LOADING:
                    break;
            }
        });
    }

    private void setUpSpinner() {

        getViewDataBinding().spinner.setOnItemSelectedListener(this);
        ArrayAdapter spinnerAdapter = new ArrayAdapter(getActivity(), android.R.layout.simple_spinner_item, sorting);
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        getViewDataBinding().spinner.setAdapter(spinnerAdapter);

    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        switch (sorting[i]) {
            case Constants.WEEKLY:
                mViewModel.getRepoList(new RepoListRequest("created:2017-05-20", "stars", "desc", 1));
                break;
            case Constants.MONTHLY:
                mViewModel.getRepoList(new RepoListRequest("created:2019-05-20", "stars", "desc", 1));
                break;
            case Constants.YEARLY:
                mViewModel.getRepoList(new RepoListRequest("created:2020-05-20", "stars", "desc", 1));
                break;
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}
