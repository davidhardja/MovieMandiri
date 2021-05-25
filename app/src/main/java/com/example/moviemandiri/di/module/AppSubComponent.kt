package com.example.moviemandiri.di.module

import com.example.moviemandiri.GenreComponent
import dagger.Module

@Module(
    subcomponents = [
        GenreComponent::class
    ]
)
class AppSubComponent