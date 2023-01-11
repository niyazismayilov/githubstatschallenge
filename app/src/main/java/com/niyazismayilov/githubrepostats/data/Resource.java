package com.niyazismayilov.githubrepostats.data;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class Resource<T> {

    public static final int INITIAL = 0;
    public static final int LOADING = 1;
    public static final int SUCCESS = 2;
    public static final int ERROR = 3;

    private final int type;
    private final T data;
    private final Throwable error;

    private Resource(@NonNull int type, @Nullable T d, @Nullable Throwable e) {
        this.type = type;
        this.data = d;
        this.error = e;
    }

    public static <T> Resource<T> initial() {
        return new Resource<>(INITIAL, null, null);
    }

    public static <T> Resource<T> loading() {
        return new Resource<>(LOADING, null, null);
    }

    public static <T> Resource<T> success(T d) {
        return new Resource<>(SUCCESS, d, null);
    }

    public static <T> Resource<T> error(Throwable e) {
        return new Resource<>(ERROR, null, e);
    }

    public int getType() {
        return type;
    }

    public T getData() {
        return data;
    }

    public Throwable getError() {
        return error;
    }
}