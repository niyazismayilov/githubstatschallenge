// Generated by Dagger (https://dagger.dev).
package com.niyazismayilov.githubrepostats.ui.base;

import androidx.databinding.ViewDataBinding;
import dagger.MembersInjector;
import dagger.internal.InjectedFieldSignature;
import javax.inject.Provider;

@SuppressWarnings({
    "unchecked",
    "rawtypes"
})
public final class BaseActivity_MembersInjector<T extends ViewDataBinding, V extends BaseViewModel> implements MembersInjector<BaseActivity<T, V>> {
  private final Provider<V> mViewModelProvider;

  public BaseActivity_MembersInjector(Provider<V> mViewModelProvider) {
    this.mViewModelProvider = mViewModelProvider;
  }

  public static <T extends ViewDataBinding, V extends BaseViewModel> MembersInjector<BaseActivity<T, V>> create(
      Provider<V> mViewModelProvider) {
    return new BaseActivity_MembersInjector<T, V>(mViewModelProvider);
  }

  @Override
  public void injectMembers(BaseActivity<T, V> instance) {
    injectMViewModel(instance, mViewModelProvider.get());
  }

  @InjectedFieldSignature("com.niyazismayilov.githubrepostats.ui.base.BaseActivity.mViewModel")
  public static <T extends ViewDataBinding, V extends BaseViewModel> void injectMViewModel(
      BaseActivity<T, V> instance, V mViewModel) {
    instance.mViewModel = mViewModel;
  }
}
