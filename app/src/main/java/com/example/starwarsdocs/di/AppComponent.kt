package com.example.starwarsdocs.di

import android.app.Application
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton


@Singleton
@Component(
    modules = [
        NetworkModule::class,
        LocalModule::class
    ]
)
interface AppComponent {
    @Component.Builder
    interface Builder {
        fun build(): AppComponent

        @BindsInstance
        fun inject(application: Application): Builder
    }
}