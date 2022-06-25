package com.example.movies_app.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class MovieEntity {

    @PrimaryKey(autoGenerate = true)
    var id: Int? = null

    @ColumnInfo(name = "icon")
    var icon: String? = null

    @ColumnInfo(name = "name")
    var name: String? = null

    @ColumnInfo(name = "sum")
    var summary: String? = null

    @ColumnInfo(name = "headline")
    var headline: String? = null


}