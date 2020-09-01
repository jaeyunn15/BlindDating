package com.project.blinddating.di.module

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.project.blinddating.di.ViewModelKey
import com.project.blinddating.di.scope.AppScoped
import com.project.blinddating.ui.activity.ViewModelFactory
import com.project.blinddating.ui.activity.chat.ChatViewModel
import com.project.blinddating.ui.activity.login.LoginViewModel
import com.project.blinddating.ui.activity.profile.ProfileViewModel
import com.project.blinddating.ui.activity.register.RegisterViewModel
import com.project.blinddating.ui.activity.waiting.WaitingViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {

    @Binds
    @AppScoped
    abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(LoginViewModel::class)
    abstract fun bindLoginViewModel(viewModel: LoginViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(RegisterViewModel::class)
    abstract fun bindRegisterViewModel(viewModel: RegisterViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(ProfileViewModel::class)
    abstract fun bindProfileViewModel(viewModel: ProfileViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(WaitingViewModel::class)
    abstract fun bindLobbyViewModel(viewModel: WaitingViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(ChatViewModel::class)
    abstract fun bindRoomViewModel(viewModel: ChatViewModel): ViewModel

}