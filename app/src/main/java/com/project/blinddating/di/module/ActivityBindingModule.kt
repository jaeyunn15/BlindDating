package com.project.blinddating.di.module

import com.project.blinddating.di.scope.ActivityScoped
import com.project.blinddating.ui.activity.chat.ChatActivity
import com.project.blinddating.ui.activity.chat.ChatModule
import com.project.blinddating.ui.activity.login.LoginActivity
import com.project.blinddating.ui.activity.login.LoginModule
import com.project.blinddating.ui.activity.register.RegisterActivity
import com.project.blinddating.ui.activity.register.RegisterModule
import com.project.blinddating.ui.activity.waiting.WaitingActivity
import com.project.blinddating.ui.activity.waiting.WaitingModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBindingModule {

    @ActivityScoped
    @ContributesAndroidInjector(modules = [LoginModule::class])
    internal abstract fun loginActivity(): LoginActivity

    @ActivityScoped
    @ContributesAndroidInjector(modules = [RegisterModule::class])
    internal abstract fun registerActivity(): RegisterActivity

    @ActivityScoped
    @ContributesAndroidInjector(modules = [WaitingModule::class])
    internal abstract fun waitingActivity(): WaitingActivity

    @ActivityScoped
    @ContributesAndroidInjector(modules = [ChatModule::class])
    internal abstract fun chatActivity(): ChatActivity

}