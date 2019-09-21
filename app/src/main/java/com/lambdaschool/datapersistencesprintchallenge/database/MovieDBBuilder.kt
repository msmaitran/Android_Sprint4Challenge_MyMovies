package com.lambdaschool.datapersistencesprintchallenge.database

import android.content.Context
import androidx.room.Room

class MovieDBBuilder(val context: Context) {

    // Build the Room database
    private val database by lazy {
        Room.databaseBuilder(
            context.applicationContext,
            MovieDB::class.java, "movie_database"
        ).fallbackToDestructiveMigration().build()
    }
}