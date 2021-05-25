package com.example.moviemandiri

import android.app.Application
import com.example.moviemandiri.di.AppComponent
import com.example.moviemandiri.di.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import javax.inject.Inject

class MainApplication : Application(), HasAndroidInjector {

    @Inject
    lateinit var androidDispatchingAndroidInjector: DispatchingAndroidInjector<Any>

    override fun androidInjector(): AndroidInjector<Any> {
        return androidDispatchingAndroidInjector
    }

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.factory().create(applicationContext)
        appComponent.inject(this)
    }

    companion object {
        lateinit var appComponent: AppComponent
    }
}