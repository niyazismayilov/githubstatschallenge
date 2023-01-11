package com.niyazismayilov.githubrepostats.ui.fragment.repolist;

import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.navigation.Navigation;

import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.niyazismayilov.githubrepostats.BR;
import com.niyazismayilov.githubrepostats.R;
import com.niyazismayilov.githubrepostats.data.Resource;
import com.niyazismayilov.githubrepostats.data.model.request.RepoListRequest;
import com.niyazismayilov.githubrepostats.databinding.FragmentRepoListBinding;
import com.niyazismayilov.githubrepostats.di.component.FragmentComponent;
import com.niyazismayilov.githubrepostats.ui.base.BaseFragment;
import com.niyazismayilov.githubrepostats.ui.fragment.repolist.adapter.RepoListAdapter;
import com.niyazismayilov.githubrepostats.utils.Constants;

public class RepoListFragment extends BaseFragment<FragmentRepoListBinding, RepoListViewModel> implements AdapterView.OnItemSelectedListener {
    private String[] sorting = {Constants.WEEKLY, Constants.MONTHLY, Constants.YEARLY};

    @Override
    public int getBindingVariable() {
        return BR.viewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_repo_list;
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
        setUpListeners();
    }

    private void setUpListeners() {
        getViewDataBinding().btFavList.setOnClickListener(listener -> Navigation.findNavController(getView()).navigate(R.id.action_repoListFragment_to_favoriteListFragment));
        getViewDataBinding().etSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // TODO document why this method is empty
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // TODO document why this method is empty
            }

            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void afterTextChanged(Editable s) {
                mViewModel.filterByQuery(s.toString());

            }
        });
    }

    private void setUpObservers() {
        mViewModel.repoListResponseMutableLiveData.observe(getViewLifecycleOwner(), result -> {
            switch (result.getType()) {
                case Resource.SUCCESS:
                    getViewDataBinding().prRepo.setVisibility(View.GONE);
                    getViewDataBinding().tvError.setVisibility(View.GONE);
                    getViewDataBinding().reposListRecycler.setAdapter(new RepoListAdapter(result.getData(), item -> showRepoItemDetail()));
                    break;
                case Resource.ERROR:
                    getViewDataBinding().prRepo.setVisibility(View.GONE);
                    getViewDataBinding().tvError.setVisibility(View.VISIBLE);
                    break;
                case Resource.LOADING:
                    getViewDataBinding().prRepo.setVisibility(View.VISIBLE);
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

    public void showRepoItemDetail() {
        final BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(getActivity());
        bottomSheetDialog.setContentView(R.layout.repo_item_bottom_sheet);
        bottomSheetDialog.show();

    }
    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {
    }
}