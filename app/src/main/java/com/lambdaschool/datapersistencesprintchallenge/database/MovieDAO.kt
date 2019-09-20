package com.lambdaschool.datapersistencesprintchallenge.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.lambdaschool.datapersistencesprintchallenge.model.FavoriteMovie

@Dao
interface MovieDAO {

    @Insert
    fun addFavoriteMovies(): List<FavoriteMovie>

    @Query("SELECT * FROM FavoriteMovie")
    fun getAllMovies(): List<FavoriteMovie>

    @Delete()
    fun deleteFavoriteMovie()

}