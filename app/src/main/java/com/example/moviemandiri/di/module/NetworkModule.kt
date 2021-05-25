package com.example.moviemandiri.di.module

import android.util.Log
import com.example.moviemandiri.BuildConfig
import com.example.moviemandiri.network.NetworkService
import com.example.moviemandiri.repository.GenreRepository
import com.example.moviemandiri.repository.GenreRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class NetworkModule {

    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient {
        val httpInterceptor = HttpLoggingInterceptor(object : HttpLoggingInterceptor.Logger {
            override fun log(message: String) {
                Log.d("Movie", message)
            }
        })

        httpInterceptor.level = HttpLoggingInterceptor.Level.BASIC

        return OkHttpClient.Builder()
            .addInterceptor(httpInterceptor)
            .build()
    }

    @Provides
    @Singleton
    fun provideRestClient(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun provideNetworkService(retrofit: Retrofit): NetworkService {
        return retrofit.create(NetworkService::class.java)
    }
}