package com.example.nelly.getpopularmovies.request

import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory


class RetrofitBuilder {

    companion object {
       // https://api.themoviedb.org/3/movie/550?api_key={api_key}
        private val BASE_URL = "https://api.themoviedb.org/3/"
        private var retrofit: Retrofit? = null

        val client: Retrofit?
            get() {
                if (retrofit == null) {
                    retrofit = Retrofit.Builder()
                            .addConverterFactory(GsonConverterFactory.create())
                            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                            .baseUrl(BASE_URL)
                            .build()
                }
                return retrofit
            }
    }
}