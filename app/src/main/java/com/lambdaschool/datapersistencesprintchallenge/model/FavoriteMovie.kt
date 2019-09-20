package com.lambdaschool.datapersistencesprintchallenge.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "movie_table")
class FavoriteMovie(

    var title: String,
    var releaseDate: String,
    var isWatched: Boolean,
    @PrimaryKey(autoGenerate = true)
    val id: Int

)