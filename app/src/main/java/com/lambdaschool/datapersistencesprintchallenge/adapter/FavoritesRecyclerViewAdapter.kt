package com.lambdaschool.datapersistencesprintchallenge.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.lambdaschool.datapersistencesprintchallenge.R
import com.lambdaschool.datapersistencesprintchallenge.model.FavoriteMovie
import com.lambdaschool.datapersistencesprintchallenge.viewmodel.MovieViewModel
import kotlinx.android.synthetic.main.favorites_list_item.view.*

class FavoritesRecyclerViewAdapter (val favoriteMovies: MutableList<FavoriteMovie>, private val movieViewModel: MovieViewModel) :
    RecyclerView.Adapter<FavoritesRecyclerViewAdapter.ViewHolder>() {

    var context: Context? = null

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val favoriteMovieName: TextView = view.tv_favorite_movie_title
        val favoriteMovieYear: TextView = view.tv_favorite_movie_year
        val favoriteWatched = view.cb_watched
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        context = parent.context
        val viewGroup = LayoutInflater.from(context).inflate(R.layout.favorites_list_item, parent, false)
        return ViewHolder(viewGroup)
    }

    override fun getItemCount(): Int {
        return favoriteMovies.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val favorite = favoriteMovies[position]
        holder.favoriteMovieName.text = favorite.title
        holder.favoriteMovieYear.text = favorite.releaseDate
        holder.favoriteWatched.isChecked = favorite.isWatched
        holder.favoriteWatched.setOnCheckedChangeListener {
                compoundButton, b -> favorite.isWatched = b
            movieViewModel.update(favorite)
            notifyItemChanged(position)
        }
        holder.favoriteMovieName.setOnLongClickListener {
            favoriteMovies.removeAt(position)
            movieViewModel.delete(favorite)
            notifyItemRemoved(position)
            true
        }
    }

}