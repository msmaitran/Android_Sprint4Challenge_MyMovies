package com.lambdaschool.datapersistencesprintchallenge.model

import androidx.annotation.NonNull
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class FavoriteMovie {

    var title: String? = null
    var releaseDate: String? = null
    var isWatched: Boolean = false
    @PrimaryKey(autoGenerate = true) @NonNull
    val id: Int

    constructor(id: Int) {
        this.id = id
        this.title = ""
        this.releaseDate = ""
        this.isWatched = false
    }
}