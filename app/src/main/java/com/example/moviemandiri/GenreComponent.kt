package com.example.moviemandiri

import dagger.Subcomponent

@Subcomponent
interface GenreComponent {

    @Subcomponent.Factory
    interface Factory {
        fun create(): GenreComponent
    }

    fun inject(activity: GenreActivity)
    fun inject(genreListFragment: GenreListFragment)
}