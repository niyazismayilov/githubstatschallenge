package com.niyazismayilov.githubrepostats.di.scope;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Scope;

import dagger.Provides;

@Scope
@Retention(RetentionPolicy.SOURCE)
public @interface ActivityScope {
}
