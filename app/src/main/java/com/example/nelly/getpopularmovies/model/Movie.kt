package com.example.nelly.getpopularmovies.model

import java.io.Serializable

val baseImageUrl = "https://image.tmdb.org/t/p/w500"

class Movie(val adult: Boolean, val backdrop_path: String, val genre_ids: List<Int>, val id: Int, val original_language: String,
            val original_title: String, val overview: String, val popularity: Double, val poster_path: String,
            val release_date: String, val title: String, val video: Boolean, val vote_average: Double, val vote_count: Int) : Serializable {

    fun setPosterPath(): String {
        return baseImageUrl + poster_path
    }

}

