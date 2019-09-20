package com.lambdaschool.datapersistencesprintchallenge.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModelProviders
import com.lambdaschool.datapersistencesprintchallenge.R
import com.lambdaschool.datapersistencesprintchallenge.model.FavoriteMovie
import com.lambdaschool.datapersistencesprintchallenge.viewmodel.MovieViewModel

class FavoritesActivity : AppCompatActivity() {

    lateinit var movieViewModel: MovieViewModel
    lateinit var favorites: LiveData<List<FavoriteMovie>>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_favorites)

        movieViewModel = ViewModelProviders.of(this).get(MovieViewModel::class.java)

        favorites = movieViewModel.getAllMovies()
    }
}
