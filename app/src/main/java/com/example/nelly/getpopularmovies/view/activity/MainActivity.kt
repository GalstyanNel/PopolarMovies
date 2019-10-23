package com.example.nelly.getpopularmovies.view.activity

import android.app.ProgressDialog
import android.os.Bundle
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.widget.Toast
import com.example.nelly.getpopularmovies.utils.Constants.Companion.PAGE_START
import com.example.nelly.getpopularmovies.R
import com.example.nelly.getpopularmovies.view.adapter.MoviesAdapter
import com.example.nelly.getpopularmovies.model.Movie
import com.example.nelly.getpopularmovies.view.MovieContractView
import com.example.nelly.getpopularmovies.view.PaginationScrollListener
import com.example.nelly.getpopularmovies.view.presenter.MoviePresenter


class MainActivity : AppCompatActivity(), MovieContractView {

    private lateinit var progressDialog: ProgressDialog
    private var recyclerView: RecyclerView? = null

    var linearLayoutManager: LinearLayoutManager? = null
    private lateinit var movieList: ArrayList<Movie>


    private var presenter: MoviePresenter? = null
    private lateinit var adapter: MoviesAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        presenter = MoviePresenter()

        presenter?.attachView(this)
        lifecycle.addObserver(presenter)

        initViews()
        showProgress()
        presenter?.loadMovies(PAGE_START)
        initAdapter();
    }

    private fun initAdapter() {
        movieList = ArrayList()
        recyclerView?.adapter = MoviesAdapter(applicationContext, movieList)
        linearLayoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

        adapter = MoviesAdapter(this, movieList)
        recyclerView?.layoutManager = linearLayoutManager
        recyclerView?.itemAnimator = DefaultItemAnimator()
        recyclerView?.adapter = adapter
        val scrollListener = PaginationScrollListener(linearLayoutManager!!, this.presenter!!)
        recyclerView?.addOnScrollListener(scrollListener)

    }

    private fun initViews() {
        val swipeContainer: SwipeRefreshLayout = findViewById(R.id.main_content)
        swipeContainer.setColorSchemeResources(android.R.color.holo_orange_dark)
        swipeContainer.setOnRefreshListener {
            showProgress()
            Toast.makeText(this@MainActivity, "Movies Refreshed", Toast.LENGTH_SHORT).show()
            swipeContainer.isRefreshing = false
            presenter?.loadMovies(PAGE_START)
        }
        progressDialog = ProgressDialog(this)
        recyclerView = findViewById(R.id.recycler_view)
    }


    override fun showMovies(movies: List<Movie>) {
        movieList.addAll(movies)
        adapter.notifyDataSetChanged()
    }


    override fun hideProgress() {
        progressDialog.dismiss()
    }

    override fun showProgress() {
        progressDialog.setMessage("Fetching movies ...")
        progressDialog.setCancelable(false)
        progressDialog.show()
    }

}