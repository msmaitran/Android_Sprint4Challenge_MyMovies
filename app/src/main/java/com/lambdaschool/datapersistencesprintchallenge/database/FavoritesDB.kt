package com.lambdaschool.datapersistencesprintchallenge.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.lambdaschool.datapersistencesprintchallenge.model.FavoriteMovie

@Database(entities = [FavoriteMovie::class], version = 2, exportSchema = true)
abstract class FavoritesDB : RoomDatabase() {
    abstract fun favoritesDao(): FavoritesDAO
}