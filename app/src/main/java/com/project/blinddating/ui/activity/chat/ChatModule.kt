package com.project.blinddating.ui.activity.chat

import com.project.blinddating.di.providers.BaseResourceProvider
import com.project.blinddating.di.providers.ResourceProvider
import com.project.blinddating.di.scope.ActivityScoped
import com.project.blinddating.di.scope.FragmentScoped
import dagger.Module
import dagger.Provides
import dagger.android.ContributesAndroidInjector

@Module(includes = [ChatModule.ChatAbstractModule::class])
class ChatModule {

    @ActivityScoped
    @Provides
    internal fun provideResourceProvider(context: ChatActivity): BaseResourceProvider {
        return ResourceProvider(context)
    }

    @Module
    interface ChatAbstractModule {
        @FragmentScoped
        @ContributesAndroidInjector
        fun registerFragment(): ChatFragment
    }
}
