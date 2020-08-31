package com.project.blinddating.di.component

import com.project.blinddating.MyApplication
import com.project.blinddating.di.module.*
import com.project.blinddating.di.scope.AppScoped
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule

@AppScoped
@Component(modules = [
    AppModule::class,
    ActivityBindingModule::class,
    AndroidSupportInjectionModule::class,
    ViewModelModule::class,
    HelperModule::class,
    ViewStateModule::class
])
interface AppComponent: AndroidInjector<MyApplication> {

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(myApplication: MyApplication) : Builder

        fun build() : AppComponent
    }

}