package com.project.blinddating.di.module

import com.project.blinddating.di.scope.AppScoped
import com.project.blinddating.ui.activity.chat.ChatViewState
import com.project.blinddating.ui.activity.login.LoginViewState
import com.project.blinddating.ui.activity.profile.ProfileViewState
import com.project.blinddating.ui.activity.register.RegisterViewState
import com.project.blinddating.ui.activity.waiting.WaitingViewState
import dagger.Module
import dagger.Provides

@Module
class ViewStateModule{

    @Provides
    @AppScoped
    fun provideLoginViewState() : LoginViewState = LoginViewState()

    @Provides
    @AppScoped
    fun provideRegisterViewState() : RegisterViewState = RegisterViewState()

    @Provides
    @AppScoped
    fun provideProfileViewState() : ProfileViewState = ProfileViewState()

    @Provides
    @AppScoped
    fun provideWaitingViewState() : WaitingViewState = WaitingViewState()

    @Provides
    @AppScoped
    fun provideChatViewState() : ChatViewState = ChatViewState()
}