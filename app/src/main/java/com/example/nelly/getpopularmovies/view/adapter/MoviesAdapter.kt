package com.example.nelly.getpopularmovies.view.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.nelly.getpopularmovies.R
import com.example.nelly.getpopularmovies.model.Movie


class MoviesAdapter(context: Context, listOfMovie: List<Movie>) : RecyclerView.Adapter<MoviesAdapter.MovieViewHolder>() {
    private var context: Context? = null
    var listOfMovies: List<Movie> = ArrayList()

    init {
        this.context = context
        this.listOfMovies = listOfMovie
    }

    override fun onBindViewHolder(holder: MovieViewHolder?, position: Int) {
        holder?.title?.text = listOfMovies[position].original_title
        val vote: String? = listOfMovies[position].vote_average.toString()
        holder?.userRating?.text = vote

        Glide.with(context!!)
                .load(listOfMovies[position].setPosterPath())
                .apply(RequestOptions().placeholder(R.drawable.load))
                .into(holder?.thumbNail!!)
    }

    override fun getItemCount(): Int {
        return listOfMovies.size
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): MovieViewHolder {
        val view = LayoutInflater.from(parent?.context)
                .inflate(R.layout.movie_card, parent, false)
        return MovieViewHolder(view)
    }

    class MovieViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView) {
        val title = itemView?.findViewById<TextView>(R.id.title)
        val userRating = itemView?.findViewById<TextView>(R.id.userrating)
        val thumbNail = itemView?.findViewById<ImageView>(R.id.tumbnail)
    }
}