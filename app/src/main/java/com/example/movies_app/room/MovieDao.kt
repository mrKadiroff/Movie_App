package com.example.movies_app.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface MovieDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addUsers(movies: MovieEntity)

    @Query("select * from movieentity")
   fun getAllMovies(): List<MovieEntity>


    @Query("DELETE FROM movieentity WHERE name=:name")
    fun  deleteByName(name:String)
}