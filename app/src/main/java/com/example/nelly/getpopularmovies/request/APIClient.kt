package com.example.nelly.getpopularmovies.request

import com.example.nelly.getpopularmovies.model.MoviesResponse
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query


interface APIClient {

    @GET("movie/popular")
    fun getPopularMovies(
            @Query("api_key") apiKey: String, @Query("page") page: Int): Observable<MoviesResponse>

}