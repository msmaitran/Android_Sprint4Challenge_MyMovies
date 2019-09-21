package com.lambdaschool.datapersistencesprintchallenge.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.lambdaschool.datapersistencesprintchallenge.R
import com.lambdaschool.datapersistencesprintchallenge.adapter.FavoritesRecyclerViewAdapter
import com.lambdaschool.datapersistencesprintchallenge.model.FavoriteMovie
import com.lambdaschool.datapersistencesprintchallenge.viewmodel.MovieViewModel
import kotlinx.android.synthetic.main.activity_favorites.*

class FavoritesActivity : AppCompatActivity() {

    lateinit var movieViewModel: MovieViewModel
    lateinit var favorites: MutableList<FavoriteMovie>
    var needsSetup = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_favorites)

        rv_favorites_list.layoutManager = LinearLayoutManager(this@FavoritesActivity)
        rv_favorites_list.setHasFixedSize(true)

        movieViewModel = ViewModelProviders.of(this).get(MovieViewModel::class.java)
        movieViewModel.getAllMovies().observe(this, Observer<MutableList<FavoriteMovie>> {
            if (needsSetup) {
                println("Setting up adapter")
                favorites = movieViewModel.getAllMovies().value!!
                rv_favorites_list.adapter = FavoritesRecyclerViewAdapter(favorites, movieViewModel)
                needsSetup = false
            }
        })
    }
}

