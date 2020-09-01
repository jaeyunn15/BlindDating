package com.project.blinddating.ui.activity.profile

import com.project.blinddating.di.providers.BaseResourceProvider
import com.project.blinddating.di.providers.ResourceProvider
import com.project.blinddating.di.scope.ActivityScoped
import com.project.blinddating.di.scope.FragmentScoped
import dagger.Module
import dagger.Provides
import dagger.android.ContributesAndroidInjector

@Module(includes = [ProfileModule.ProfileAbstractModule::class])
class ProfileModule {

    @ActivityScoped
    @Provides
    internal fun provideResourceProvider(context: ProfileActivity): BaseResourceProvider {
        return ResourceProvider(context)
    }

    @Module
    interface ProfileAbstractModule {
        @FragmentScoped
        @ContributesAndroidInjector
        fun registerFragment(): ProfileFragment
    }
}
