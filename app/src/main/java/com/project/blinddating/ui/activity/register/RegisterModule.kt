package com.project.blinddating.ui.activity.register

import com.project.blinddating.di.providers.BaseResourceProvider
import com.project.blinddating.di.providers.ResourceProvider
import com.project.blinddating.di.scope.ActivityScoped
import com.project.blinddating.di.scope.FragmentScoped
import dagger.Module
import dagger.Provides
import dagger.android.ContributesAndroidInjector

@Module(includes = [RegisterModule.RegisterAbstractModule::class])
class RegisterModule {

    @ActivityScoped
    @Provides
    internal fun provideResourceProvider(context: RegisterActivity) : BaseResourceProvider {
        return ResourceProvider(context)
    }

    @Module
    interface RegisterAbstractModule {
        @FragmentScoped
        @ContributesAndroidInjector
        fun registerFragment() : RegisterFragment
    }
}