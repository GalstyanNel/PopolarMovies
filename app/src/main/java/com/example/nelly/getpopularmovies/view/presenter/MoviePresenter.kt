package com.example.nelly.getpopularmovies.view.presenter

import android.arch.lifecycle.Lifecycle
import android.arch.lifecycle.LifecycleObserver
import android.arch.lifecycle.OnLifecycleEvent
import com.example.nelly.getpopularmovies.BuildConfig
import com.example.nelly.getpopularmovies.utils.Constants
import com.example.nelly.getpopularmovies.view.MovieContractView
import com.example.nelly.getpopularmovies.view.PaginationScrollListener
import com.example.nelly.getpopularmovies.request.APIClient
import com.example.nelly.getpopularmovies.request.RetrofitBuilder
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers


class MoviePresenter() : PaginationScrollListener.OnLoadMoreListener, LifecycleObserver {

    private var view: MovieContractView? = null
    private var request: APIClient? = null
    private var disposible: Disposable? = null
    private var isLoading: Boolean = false
    private var currentPage = Constants.PAGE_START

    init {
        request = RetrofitBuilder.client?.create(APIClient::class.java)

    }

    fun attachView(view: MovieContractView) {
        this.view = view
    }


    fun loadMovies(currentPage: Int) {
        val getMoviesRequest = request?.getPopularMovies(BuildConfig.THE_MOVIE_DB_API_TOKEN, currentPage)
        if (getMoviesRequest != null) {
            disposible = getMoviesRequest
                    .flatMap { Observable.fromIterable(it.results).filter({ it.adult == false }) }
                    .toList()
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe({
                        isLoading = false;
                        view?.showMovies(it);
                        view?.hideProgress()
                    }, { error ->
                        view?.hideProgress()
                    })
        }
    }

    override fun loadMoreItems() {
        isLoading = true
        currentPage += 1
        loadMovies(currentPage)
    }

    override fun isLoading(): Boolean {
        return isLoading
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    fun onDestroy() {
        if (disposible != null){
            disposible!!.dispose()
        }

    }

}