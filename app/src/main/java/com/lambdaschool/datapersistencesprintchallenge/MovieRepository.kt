package com.lambdaschool.datapersistencesprintchallenge

import android.content.Context
import android.os.AsyncTask
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

    fun getAllMovies(): LiveData<List<FavoriteMovie>> {
        return movieList
    }

    fun insert(movie: FavoriteMovie) {
        val insertMovieAsyncTask = InsertMovieAsyncTask(movieDao).execute(movie)
    }

    fun update(movie: FavoriteMovie) {
        val updateMovieAsyncTask = UpdateMovieAsyncTask(movieDao).execute(movie)
    }

    fun delete(movie: FavoriteMovie) {
        val deleteMovieAsyncTask = DeleteMovieAsyncTask(movieDao).execute(movie)
    }

    companion object {

        private class InsertMovieAsyncTask(movieDAO: MovieDAO) : AsyncTask<FavoriteMovie, Unit, Unit>() {
            override fun doInBackground(vararg p0: FavoriteMovie?) {
                MovieDAO.insert(p0[0]!!)
            }
            val MovieDAO = movieDAO
        }

        private class UpdateMovieAsyncTask(movieDAO: MovieDAO) : AsyncTask<FavoriteMovie, Unit, Unit>() {
            override fun doInBackground(vararg p0: FavoriteMovie?) {
                MovieDAO.update(p0[0]!!)
            }
            val MovieDAO = movieDAO
        }

        private class DeleteMovieAsyncTask(movieDAO: MovieDAO) : AsyncTask<FavoriteMovie, Unit, Unit>() {
            override fun doInBackground(vararg p0: FavoriteMovie?) {
                MovieDao.delete(p0[0]!!)
            }
            val MovieDao = movieDAO
        }
    }
}