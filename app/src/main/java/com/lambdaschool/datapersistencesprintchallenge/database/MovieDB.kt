package com.lambdaschool.datapersistencesprintchallenge.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.lambdaschool.datapersistencesprintchallenge.model.FavoriteMovie

@Database(entities = [FavoriteMovie::class], version = 2, exportSchema = true)
abstract class MovieDB : RoomDatabase() {
    
    abstract fun movieDao(): MovieDAO

    companion object {
        private var instance: MovieDB? = null

        fun getInstance(context: Context): MovieDB? {
            if (instance == null) {
                synchronized(MovieDB::class) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        MovieDB::class.java, "movie_database"
                    )
                        .fallbackToDestructiveMigration()
                        .build()
                }
            }
            return instance as MovieDB
        }

        fun destroyInstance() {
            instance = null
        }
    }
}