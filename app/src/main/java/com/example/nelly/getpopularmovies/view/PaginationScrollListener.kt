package com.example.nelly.getpopularmovies.view

import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView


class PaginationScrollListener(linearLayoutManager: LinearLayoutManager, onLoadMoreListener: OnLoadMoreListener) : RecyclerView.OnScrollListener() {
    var layoutManager: LinearLayoutManager? = linearLayoutManager
    var listener: OnLoadMoreListener = onLoadMoreListener

    private val count: Int = 15

    override fun onScrolled(recyclerView: RecyclerView?, dx: Int, dy: Int) {
        super.onScrolled(recyclerView, dx, dy)

        val visibleItemCount = layoutManager?.childCount
        val totalItemCount = layoutManager?.itemCount
        val firstVisibleItemPosition = layoutManager?.findFirstVisibleItemPosition()?.plus(2)

        if (!listener.isLoading()) {
            if ((visibleItemCount!! + firstVisibleItemPosition!!) >= totalItemCount!!
                    && (firstVisibleItemPosition >= 0)
                    && totalItemCount >= count) {
                listener.loadMoreItems()
            }
        }
    }

    interface OnLoadMoreListener {
        fun loadMoreItems()
        fun isLoading(): Boolean
    }

}