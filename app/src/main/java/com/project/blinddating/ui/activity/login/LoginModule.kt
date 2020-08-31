package com.project.blinddating.ui.activity.login

import com.project.blinddating.di.providers.BaseResourceProvider
import com.project.blinddating.di.providers.ResourceProvider
import com.project.blinddating.di.scope.ActivityScoped
import com.project.blinddating.di.scope.FragmentScoped
import dagger.Module
import dagger.Provides
import dagger.android.ContributesAndroidInjector

@Module(includes = [LoginModule.LoginAbstractModule::class])
class LoginModule {

    @ActivityScoped
    @Provides
    internal fun provideResourceProvider(context: LoginActivity): BaseResourceProvider {
        return ResourceProvider(context)
    }

    @Module
    interface LoginAbstractModule {
        @FragmentScoped
        @ContributesAndroidInjector
        fun loginFragment(): LoginFragment
    }
}