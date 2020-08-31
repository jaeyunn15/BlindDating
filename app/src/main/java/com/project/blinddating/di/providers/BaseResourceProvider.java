package com.project.blinddating.di.providers;

import androidx.annotation.NonNull;
import androidx.annotation.StringRes;

public interface BaseResourceProvider {
    @NonNull
    String getString(@StringRes final int id);


    @NonNull
    String getString(@StringRes final int resId, final Object... formatArgs);
}
