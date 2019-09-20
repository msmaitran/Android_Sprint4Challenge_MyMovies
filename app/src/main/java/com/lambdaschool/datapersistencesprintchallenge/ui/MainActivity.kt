package com.lambdaschool.datapersistencesprintchallenge.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.lambdaschool.datapersistencesprintchallenge.R
import com.lambdaschool.datapersistencesprintchallenge.adapter.FavoritesRecyclerViewAdapter
import com.lambdaschool.datapersistencesprintchallenge.model.FavoriteMovie
import com.lambdaschool.datapersistencesprintchallenge.retrofit.MoviesAPI
import com.lambdaschool.datapersistencesprintchallenge.viewmodel.MovieViewModel
import com.lambdaschool.sprint4challenge_mymovies.apiaccess.MovieConstants
import com.lambdaschool.sprint4challenge_mymovies.model.MovieOverview
import com.lambdaschool.sprint4challenge_mymovies.model.MovieSearchResult
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity(), Callback<MovieSearchResult> {

    lateinit var movieList: MovieSearchResult
    lateinit var moviesAPI: MoviesAPI
    lateinit var movieViewModel: MovieViewModel
    lateinit var selectedMovie: FavoriteMovie

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
            movieLayout(movieList.results)
            
        } else {
            val response = "Response not successful; ${response.errorBody().toString()}"
            Toast.makeText(this@MainActivity, response, Toast.LENGTH_SHORT).show()
        }
    }

    private fun searchMovie(movieName: String) {
        moviesAPI.getMovieList(movieName, MovieConstants.API_KEY_PARAM).enqueue(this)
    }

    fun movieLayout(movies: List<MovieOverview>) {
        rv_movie_list.removeAllViews()
        movies.forEach { movie ->
            rv_movie_list.addView(buildItemView(movie))
        }
    }

    private fun buildItemView(movie: MovieOverview): TextView {
        var listView = TextView(this)
        listView.text = movie.title
        listView.setOnClickListener {
            val favoriteMovie = FavoriteMovie(movie.title, movie.release_date, false, movie.id)
            val selected = resources.getColor(R.color.colorAccent)
            listView.setBackgroundColor(selected)
            movieViewModel.insert(favoriteMovie)
        }
        return listView
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        moviesAPI = MoviesAPI.create()

//        rv_movie_list.apply {
//            layoutManager = LinearLayoutManager(this@MainActivity)
//            adapter = FavoritesRecyclerViewAdapter(movieList)
//        }

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
