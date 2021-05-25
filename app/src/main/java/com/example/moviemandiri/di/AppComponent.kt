package com.example.moviemandiri.di

import android.content.Context
import com.example.moviemandiri.MovieListActivity
import com.example.moviemandiri.GenreActivity
import com.example.moviemandiri.MainApplication
import com.example.moviemandiri.MovieDetailActivity
import com.example.moviemandiri.di.module.DataBinderModule
import com.example.moviemandiri.di.module.NetworkModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidInjectionModule::class,
        NetworkModule::class,
        DataBinderModule::class
    ]
)
interface AppComponent {

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance context: Context): AppComponent
    }

    fun inject(app: MainApplication)

    fun injectMainActivity(activity: GenreActivity)

    fun injectDiscoverActivity(activity: MovieListActivity)

    fun injectDetailActivity(activity: MovieDetailActivity)
}