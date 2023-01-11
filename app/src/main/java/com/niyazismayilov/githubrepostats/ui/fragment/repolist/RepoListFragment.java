package com.niyazismayilov.githubrepostats.ui.fragment.repolist;

import android.annotation.SuppressLint;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.niyazismayilov.githubrepostats.BR;
import com.niyazismayilov.githubrepostats.R;
import com.niyazismayilov.githubrepostats.data.Resource;
import com.niyazismayilov.githubrepostats.data.model.request.RepoListRequest;
import com.niyazismayilov.githubrepostats.data.model.response.RepoItem;
import com.niyazismayilov.githubrepostats.databinding.FragmentRepoListBinding;
import com.niyazismayilov.githubrepostats.di.component.FragmentComponent;
import com.niyazismayilov.githubrepostats.ui.base.BaseFragment;
import com.niyazismayilov.githubrepostats.ui.fragment.repolist.adapter.RepoListAdapter;
import com.niyazismayilov.githubrepostats.utils.Constants;

public class RepoListFragment extends BaseFragment<FragmentRepoListBinding, RepoListViewModel> implements AdapterView.OnItemSelectedListener {
    private String[] sorting = {Constants.WEEKLY, Constants.MONTHLY, Constants.DAY};
    public RepoListRequest repoListRequest;
    boolean isLoading = false;

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
        repoListRequest=new RepoListRequest(mViewModel.getLastDateTime(Constants.WEEKLY), "stars", "desc", 1);
        mViewModel.getRepoList(repoListRequest);
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
        getViewDataBinding().reposListRecycler.setAdapter(new RepoListAdapter(mViewModel.cachedList, item -> showRepoItemDetail(item)));
        getViewDataBinding().btFavList.setOnClickListener(listener -> Navigation.findNavController(getView()).navigate(R.id.action_repoListFragment_to_favoriteListFragment));
        getViewDataBinding().etSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void afterTextChanged(Editable s) {
                mViewModel.filterByQuery(s.toString());

            }
        });
    }

    @SuppressLint("NotifyDataSetChanged")
    private void setUpObservers() {
        mViewModel.repoListResponseMutableLiveData.observe(getViewLifecycleOwner(), result -> {
            switch (result.getType()) {
                case Resource.SUCCESS:
                    getViewDataBinding().prRepo.setVisibility(View.GONE);
                    getViewDataBinding().tvError.setVisibility(View.GONE);
                    getViewDataBinding().reposListRecycler.getAdapter().notifyDataSetChanged();
                    initScrollListener();
                    isLoading =false;
                    break;
                case Resource.ERROR:
                    getViewDataBinding().reposListRecycler.setVisibility(View.GONE);
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
        mViewModel.clearList();
        switch (sorting[i]) {
            case Constants.WEEKLY:
                repoListRequest.setCreatedAt(mViewModel.getLastDateTime(Constants.WEEKLY));
                mViewModel.getRepoList(repoListRequest);
                break;
            case Constants.MONTHLY:
                repoListRequest.setCreatedAt(mViewModel.getLastDateTime(Constants.MONTHLY));
                mViewModel.getRepoList(repoListRequest);
                break;
            case Constants.DAY:
                repoListRequest.setCreatedAt(mViewModel.getLastDateTime(Constants.DAY));
                mViewModel.getRepoList(repoListRequest);
                break;
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {
    }

    private void initScrollListener() {
        getViewDataBinding().reposListRecycler.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                LinearLayoutManager linearLayoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();

                if (!isLoading) {
                    if (linearLayoutManager != null && linearLayoutManager.findLastCompletelyVisibleItemPosition() == getViewDataBinding().reposListRecycler.getAdapter().getItemCount() - 1) {
                        repoListRequest.goNextPage();
                        mViewModel.getRepoList(repoListRequest);
                        isLoading = true;
                    }
                }
            }
        });


    }
    public  void showRepoItemDetail(RepoItem item) {
        final BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(getActivity());
        bottomSheetDialog.setContentView(R.layout.repo_item_bottom_sheet);
        ((TextView)bottomSheetDialog.findViewById(R.id.tv_name)).setText(item.getName());
        ((TextView)bottomSheetDialog.findViewById(R.id.tvLogin)).setText(item.getRepoOwner().getLogin());
        ((TextView)bottomSheetDialog.findViewById(R.id.tv_subtitle)).setText(item.getDescription());
        ((TextView)bottomSheetDialog.findViewById(R.id.tv_stars)).setText(item.getStars());
        ((TextView)bottomSheetDialog.findViewById(R.id.tv_language)).setText(new StringBuilder().append(getString(R.string.language)).append(item.getLanguage()).toString());
        ((TextView)bottomSheetDialog.findViewById(R.id.tv_forks)).setText(new StringBuilder().append(getString(R.string.fork)).append(item.getForks()).toString());
        ((TextView)bottomSheetDialog.findViewById(R.id.tv_createDate)).setText(new StringBuilder().append(getString(R.string.created_at)).append(item.getCreated_at()).toString());
        ((TextView)bottomSheetDialog.findViewById(R.id.tv_repoLink)).setText(new StringBuilder().append(getString(R.string.html_url)).append(item.getRepoOwner().getHtml_url()).toString());
        ((Button) bottomSheetDialog.findViewById(R.id.bt_makeFav)).setOnClickListener(view ->{
            mViewModel.addToFavorites(item);
            Toast.makeText(getActivity(), getString(R.string.repo_added_info),Toast.LENGTH_LONG).show();
        });
        Glide.with(this)
                .load(item.getRepoOwner().getAvatar_url())
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .placeholder(R.drawable.no_image)
                .into((ImageView)bottomSheetDialog.findViewById(R.id.iv_avatar));
        bottomSheetDialog.show();
    }
}
