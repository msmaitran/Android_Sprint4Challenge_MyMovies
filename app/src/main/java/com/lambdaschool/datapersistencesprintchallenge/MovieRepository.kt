package com.lambdaschool.datapersistencesprintchallenge

import android.content.Context
import androidx.lifecycle.LiveData
import com.lambdaschool.datapersistencesprintchallenge.database.MovieDAO
import com.lambdaschool.datapersistencesprintchallenge.database.MovieDB
import com.lambdaschool.datapersistencesprintchallenge.model.FavoriteMovie

class MovieRepository(context: Context) {

    private var movieDao: MovieDAO

    private var movieList: LiveData<List<FavoriteMovie>>

    init {
        val database: MovieDB = MovieDB.getInstance(context)!!
        movieDao = database.movieDao()
        movieList = movieDao.getAllMovies()
    }


}