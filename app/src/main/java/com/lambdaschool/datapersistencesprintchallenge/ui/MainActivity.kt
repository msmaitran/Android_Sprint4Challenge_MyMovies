package com.lambdaschool.datapersistencesprintchallenge.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.lambdaschool.datapersistencesprintchallenge.R
import com.lambdaschool.datapersistencesprintchallenge.retrofit.MoviesAPI
import com.lambdaschool.sprint4challenge_mymovies.apiaccess.MovieConstants
import com.lambdaschool.sprint4challenge_mymovies.model.MovieOverview
import com.lambdaschool.sprint4challenge_mymovies.model.MovieSearchResult
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity(), Callback<MovieSearchResult> {

    lateinit var moviesAPI: MoviesAPI

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
