package com.example.movies_app.bottomFragments.fragments

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.movies_app.R

class ChildActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_child)
    }
}