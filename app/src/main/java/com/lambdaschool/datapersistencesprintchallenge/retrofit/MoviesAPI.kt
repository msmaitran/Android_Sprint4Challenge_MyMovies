package com.lambdaschool.datapersistencesprintchallenge.retrofit

import com.google.gson.GsonBuilder
import com.lambdaschool.sprint4challenge_mymovies.model.MovieSearchResult
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query
import java.util.concurrent.TimeUnit

interface MoviesAPI {

    @GET("search/movie?")
    fun getMovieList(@Query("query") movieName: String, @Query("api_key") apiKey: String): Call<MovieSearchResult>

    companion object {
        private const val BASE_URL = "https://developers.themoviedb.org/3/search/"

        fun create(): MoviesAPI {
            val logger = HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BASIC
                level = HttpLoggingInterceptor.Level.BODY
            }

            val gson = GsonBuilder()
                .setLenient()
                .create()

            val okHttpClient = OkHttpClient.Builder()
                .addInterceptor(logger)
                .retryOnConnectionFailure(false)
                .readTimeout(10, TimeUnit.SECONDS)
                .connectTimeout(15, TimeUnit.SECONDS)
                .build()

            val retrofit = Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build()
            return retrofit.create(MoviesAPI::class.java)
        }
    }
}