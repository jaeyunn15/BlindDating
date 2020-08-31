package com.project.blinddating.ui.activity.waiting

import com.project.blinddating.di.providers.BaseResourceProvider
import com.project.blinddating.di.providers.ResourceProvider
import com.project.blinddating.di.scope.ActivityScoped
import com.project.blinddating.di.scope.FragmentScoped
import dagger.Module
import dagger.Provides
import dagger.android.ContributesAndroidInjector

@Module(includes = [WaitingModule.WaitingAbstractModule::class])
class WaitingModule {

    @ActivityScoped
    @Provides
    internal fun provideResourceProvider(context: WaitingActivity): BaseResourceProvider {
        return ResourceProvider(context)
    }

    @Module
    interface WaitingAbstractModule {
        @FragmentScoped
        @ContributesAndroidInjector
        fun waitingFragment(): WaitingFragment
    }
}