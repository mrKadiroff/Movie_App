package com.example.movies_app.bottomFragments.fragments

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.movies_app.databinding.ActivityChildBinding
import com.example.movies_app.network.allmovies.Result
import com.example.movies_app.room.AppDatabase
import com.example.movies_app.room.MovieEntity


class ChildActivity : AppCompatActivity() {
    lateinit var binding: ActivityChildBinding
    lateinit var appDatabase: AppDatabase
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityChildBinding.inflate(layoutInflater)
        setContentView(binding.root)
        appDatabase = AppDatabase.getInstance(binding.root.context)

        val rec = intent.getSerializableExtra("rec")
        val new = intent.getSerializableExtra("new")

        if (rec !=null){
            AllMovies()
        }

        if (new!=null){
            NewMovies()
        }







    }

    private fun NewMovies() {
        val recent = intent.getSerializableExtra("news") as com.example.movies_app.network.newresult.Result
        val position = intent.getSerializableExtra("num2")
        try {
            Glide.with(binding.root.context).load(recent.multimedia.src).into(binding.profilepicture);
        }catch (e:Exception){
            Toast.makeText(binding.root.context, e.message, Toast.LENGTH_SHORT).show()
        }

        binding.movieNae.text = recent.display_title
        binding.summary.text = recent.headline
        binding.headline.text = recent.summary_short

        var a = 100
        binding.bookmark.setOnClickListener {

            if (a==position) {
                appDatabase.movieDao().deleteByName(recent.display_title)
                Toast.makeText(binding.root.context, "Deleted", Toast.LENGTH_SHORT).show()
                a++
            } else {
                val movieEntity = MovieEntity()
                movieEntity.icon = recent.multimedia.src
                movieEntity.headline = recent.headline
                movieEntity.name = recent.display_title
                movieEntity.summary = recent.summary_short
                appDatabase.movieDao().addUsers(movieEntity)
                Toast.makeText(binding.root.context, "Added to list", Toast.LENGTH_SHORT).show()
                a=position.toString().toInt()
            }



        }


    }

    private fun AllMovies() {
        val recent = intent.getSerializableExtra("recent") as Result
        val position = intent.getSerializableExtra("num1")


        try {
            Glide.with(binding.root.context).load(recent.multimedia.src).into(binding.profilepicture);
        }catch (e:Exception){
            Toast.makeText(binding.root.context, e.message, Toast.LENGTH_SHORT).show()
        }

        binding.movieNae.text = recent.display_title
        binding.summary.text = recent.headline
        binding.headline.text = recent.summary_short


        var a = 100
        binding.bookmark.setOnClickListener {

            if (a==position) {
                appDatabase.movieDao().deleteByName(recent.display_title)
                Toast.makeText(binding.root.context, "Deleted", Toast.LENGTH_SHORT).show()
                a++
            } else {
                val movieEntity = MovieEntity()
                movieEntity.icon = recent.multimedia.src
                movieEntity.headline = recent.headline
                movieEntity.name = recent.display_title
                movieEntity.summary = recent.summary_short
                appDatabase.movieDao().addUsers(movieEntity)
                Toast.makeText(binding.root.context, "Added to list", Toast.LENGTH_SHORT).show()
                a=position.toString().toInt()
            }



        }


    }
}