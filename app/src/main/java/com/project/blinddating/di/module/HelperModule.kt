package com.project.blinddating.di.module

import com.project.blinddating.di.scope.AppScoped
import com.project.blinddating.helper.FirebaseHelper
import dagger.Module
import dagger.Provides

@Module
class HelperModule {

    @Provides
    @AppScoped
    fun provideFirebaseHelper(): FirebaseHelper = FirebaseHelper()

}