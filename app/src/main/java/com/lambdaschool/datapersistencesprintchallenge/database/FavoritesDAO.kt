package com.lambdaschool.datapersistencesprintchallenge.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.lambdaschool.datapersistencesprintchallenge.model.FavoriteMovie

@Dao
interface FavoritesDAO {

    @Insert
    fun addFavoriteMovie(): List<FavoriteMovie>

    @Query("SELECT * FROM FavoriteMovie")
    fun getAllFavoriteMovies(): List<FavoriteMovie>

    @Delete()
    fun deleteFavoriteMovie()

}