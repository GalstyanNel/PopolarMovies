package com.example.nelly.getpopularmovies.view

import com.example.nelly.getpopularmovies.model.Movie


interface MovieContractView {
    fun showMovies(movies: List<Movie>)
    fun showProgress()
    fun hideProgress()
}