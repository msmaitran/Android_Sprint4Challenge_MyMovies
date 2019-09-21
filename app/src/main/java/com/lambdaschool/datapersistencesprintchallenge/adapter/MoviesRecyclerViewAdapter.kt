package com.lambdaschool.datapersistencesprintchallenge.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.lambdaschool.datapersistencesprintchallenge.R
import com.lambdaschool.sprint4challenge_mymovies.model.MovieOverview
import kotlinx.android.synthetic.main.movies_list_item.view.*

class MoviesRecyclerViewAdapter (val searchedMovie: MutableList<MovieOverview>) :
    RecyclerView.Adapter<MoviesRecyclerViewAdapter.ViewHolder>() {

    var context: Context? = null

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val movieName: TextView = view.tv_movie_title
        val movieYear: TextView = view.tv_year
        val movieSelected: CardView = view.cv_movie
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        context = parent.context
        val viewGroup = LayoutInflater.from(context).inflate(R.layout.movies_list_item, parent, false)
        return ViewHolder(viewGroup)
    }

    override fun getItemCount(): Int {
        return searchedMovie.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val movie = searchedMovie[position]
        holder.movieName.text = movie.title
        holder.movieYear.text = movie.release_date
        holder.movieSelected.setOnClickListener {
            Toast.makeText(it.context,"${movie.title} was added to Favorite Movies", Toast.LENGTH_SHORT).show()
        }
    }
}