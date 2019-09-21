package com.lambdaschool.datapersistencesprintchallenge.database

import androidx.lifecycle.LiveData
import androidx.room.*
import com.lambdaschool.datapersistencesprintchallenge.model.FavoriteMovie

@Dao
interface MovieDAO {

    @Query("SELECT * FROM movie_table")
    fun getAllMovies(): LiveData<MutableList<FavoriteMovie>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(movie: FavoriteMovie)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun update(movie: FavoriteMovie)

    @Delete()
    fun delete(movie: FavoriteMovie)

}