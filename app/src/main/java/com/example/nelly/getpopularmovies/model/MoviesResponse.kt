package com.example.nelly.getpopularmovies.model

import java.io.Serializable


data class MoviesResponse(val page: Int, val total_results: Int, val total_pages: Int, val results: List<Movie> ) : Serializable