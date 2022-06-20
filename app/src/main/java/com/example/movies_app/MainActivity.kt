package com.example.movies_app

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.movies_app.bottomFragments.FeedFragment
import com.example.movies_app.bottomFragments.ProfileFragment
import com.example.movies_app.bottomFragments.SavedFragment
import com.example.movies_app.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    val fragment = FeedFragment()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        openMainFragment()


        binding.bottomBar.setItemSelected(R.id.feede)
        binding.bottomBar.setOnItemSelectedListener {
            when (it) {

                R.id.feede -> {
                    openMainFragment()
                }

                R.id.saved -> {
                    val savedFragment = SavedFragment()
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.fragment_container2, savedFragment).commit()
                }

                R.id.profile -> {
                    val profileFragment = ProfileFragment()
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.fragment_container2, profileFragment).commit()
                }
            }
        }



    }

    private fun openMainFragment() {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.fragment_container2, fragment)
        transaction.commit()
    }

}