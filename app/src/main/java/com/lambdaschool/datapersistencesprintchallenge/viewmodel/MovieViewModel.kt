package com.lambdaschool.datapersistencesprintchallenge.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.lambdaschool.datapersistencesprintchallenge.MovieRepository
import com.lambdaschool.datapersistencesprintchallenge.model.FavoriteMovie

class MovieViewModel (application: Application ): AndroidViewModel(application) {

    private var repository: MovieRepository = MovieRepository(application)

    private var movieList: LiveData<List<FavoriteMovie>> = repository.getAllMovies()

    fun getAllMovies(): LiveData<List<FavoriteMovie>> {
        return movieList
    }

    fun insert(movie: FavoriteMovie) {
        repository.insert(movie)
    }

    fun update(movie: FavoriteMovie) {
        repository.update(movie)
    }

    fun delete(movie: FavoriteMovie) {
        repository.delete(movie)
    }

}