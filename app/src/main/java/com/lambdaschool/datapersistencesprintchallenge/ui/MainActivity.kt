package com.lambdaschool.datapersistencesprintchallenge.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.lambdaschool.datapersistencesprintchallenge.R
import com.lambdaschool.datapersistencesprintchallenge.adapter.MoviesRecyclerViewAdapter
import com.lambdaschool.datapersistencesprintchallenge.retrofit.MoviesAPI
import com.lambdaschool.datapersistencesprintchallenge.viewmodel.MovieViewModel
import com.lambdaschool.sprint4challenge_mymovies.apiaccess.MovieConstants
import com.lambdaschool.sprint4challenge_mymovies.model.MovieSearchResult
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity(), Callback<MovieSearchResult> {

    lateinit var movieList: MovieSearchResult
    lateinit var moviesAPI: MoviesAPI
    lateinit var movieViewModel: MovieViewModel
    lateinit var moviesRecyclerViewAdapter: MoviesRecyclerViewAdapter

    override fun onFailure(call: Call<MovieSearchResult>, t: Throwable) {
        t.printStackTrace()
        val response = "Failure; ${t.printStackTrace()}"
        Toast.makeText(this@MainActivity, response, Toast.LENGTH_SHORT).show()
    }

    override fun onResponse(call: Call<MovieSearchResult>, response: Response<MovieSearchResult>) {
        if (response.isSuccessful) {
            response.body()?.results?.forEach {
                println(it.title)
            }
            movieList = response.body() as MovieSearchResult
            moviesRecyclerViewAdapter = MoviesRecyclerViewAdapter(movieList.results, movieViewModel)
            rv_movie_list.adapter = moviesRecyclerViewAdapter
            
        } else {
            val response = "Response not successful; ${response.errorBody().toString()}"
            Toast.makeText(this@MainActivity, response, Toast.LENGTH_SHORT).show()
        }
    }

    private fun searchMovie(movieName: String) {
        moviesAPI.getMovieList(movieName, MovieConstants.API_KEY_PARAM).enqueue(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        moviesAPI = MoviesAPI.create()

        rv_movie_list.layoutManager = LinearLayoutManager(this@MainActivity)
        rv_movie_list.setHasFixedSize(true)

        movieViewModel = ViewModelProviders.of(this).get(MovieViewModel::class.java)

        btn_search.setOnClickListener {
            val movieText = et_search.text.toString()
            searchMovie(movieText)
        }

        btn_favorites.setOnClickListener {
            val intent = Intent(this, FavoritesActivity::class.java)
            startActivity(intent)
        }
    }
}
