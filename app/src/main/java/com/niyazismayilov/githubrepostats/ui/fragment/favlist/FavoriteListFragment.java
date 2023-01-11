package com.niyazismayilov.githubrepostats.ui.fragment.favlist;

import android.os.Build;
import android.os.Bundle;
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

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.niyazismayilov.githubrepostats.BR;
import com.niyazismayilov.githubrepostats.R;
import com.niyazismayilov.githubrepostats.data.Resource;
import com.niyazismayilov.githubrepostats.data.model.request.RepoListRequest;
import com.niyazismayilov.githubrepostats.data.model.response.RepoItem;
import com.niyazismayilov.githubrepostats.databinding.FragmentFavListBinding;
import com.niyazismayilov.githubrepostats.databinding.FragmentRepoListBinding;
import com.niyazismayilov.githubrepostats.di.component.FragmentComponent;
import com.niyazismayilov.githubrepostats.ui.base.BaseFragment;
import com.niyazismayilov.githubrepostats.ui.fragment.repolist.RepoListViewModel;
import com.niyazismayilov.githubrepostats.ui.fragment.repolist.adapter.RepoListAdapter;
import com.niyazismayilov.githubrepostats.utils.Constants;

public class FavoriteListFragment extends BaseFragment<FragmentFavListBinding, FavoriteListViewModel> {

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
                    getViewDataBinding().reposListRecycler.setAdapter(new RepoListAdapter(result.getData(), item -> showRepoItemDetail(item)));
                    break;
                case Resource.ERROR:
                    break;
                case Resource.LOADING:
                    break;
            }
        });
    }

    public void showRepoItemDetail(RepoItem item) {

        final BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(getActivity());
        bottomSheetDialog.setContentView(R.layout.fav_item_bottom_sheet);
        ((TextView) bottomSheetDialog.findViewById(R.id.tv_name)).setText(item.getName());
        ((TextView) bottomSheetDialog.findViewById(R.id.tvLogin)).setText(item.getRepoOwner().getLogin());
        ((TextView) bottomSheetDialog.findViewById(R.id.tv_subtitle)).setText(item.getDescription());
        ((TextView) bottomSheetDialog.findViewById(R.id.tv_stars)).setText(item.getStars());
        ((TextView) bottomSheetDialog.findViewById(R.id.tv_language)).setText(new StringBuilder().append(getString(R.string.language)).append(item.getLanguage()).toString());
        ((TextView) bottomSheetDialog.findViewById(R.id.tv_forks)).setText(new StringBuilder().append(getString(R.string.fork)).append(item.getForks()).toString());
        ((TextView) bottomSheetDialog.findViewById(R.id.tv_createDate)).setText(new StringBuilder().append(getString(R.string.created_at)).append(item.getCreated_at()).toString());
        ((TextView) bottomSheetDialog.findViewById(R.id.tv_repoLink)).setText(new StringBuilder().append(getString(R.string.html_url)).append(item.getRepoOwner().getHtml_url()).toString());
        ((Button) bottomSheetDialog.findViewById(R.id.bt_makeFav)).setOnClickListener(view -> {
            mViewModel.removeFav(item);
            Toast.makeText(getActivity(), getString(R.string.repo_deleted_info), Toast.LENGTH_LONG).show();
            bottomSheetDialog.dismiss();
        });
        Glide.with(this)
                .load(item.getRepoOwner().getAvatar_url())
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .placeholder(R.drawable.no_image)
                .into((ImageView) bottomSheetDialog.findViewById(R.id.iv_avatar));

        bottomSheetDialog.show();
    }

}
