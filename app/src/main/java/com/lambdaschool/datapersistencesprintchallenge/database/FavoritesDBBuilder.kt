package com.lambdaschool.datapersistencesprintchallenge.database

import android.content.Context
import androidx.room.Room

class FavoritesDBBuilder(val context: Context) {

    private val database by lazy {
        Room.databaseBuilder(
            context.applicationContext,
            MovieDB::class.java, "favorites_database"
        ).fallbackToDestructiveMigration().build()
    }
}