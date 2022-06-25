package com.example.movies_app.bottomFragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.movies_app.R
import com.example.movies_app.adapters.RoomAdapter
import com.example.movies_app.databinding.FragmentFeedBinding
import com.example.movies_app.databinding.FragmentSavedBinding
import com.example.movies_app.room.AppDatabase
import com.example.movies_app.room.MovieEntity

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [SavedFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class SavedFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    lateinit var binding: FragmentSavedBinding
    lateinit var appDatabase: AppDatabase
    lateinit var roomAdapter: RoomAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentSavedBinding.inflate(layoutInflater, container, false)
        appDatabase = AppDatabase.getInstance(binding.root.context)
        val allMovies = appDatabase.movieDao().getAllMovies()

        roomAdapter = RoomAdapter(allMovies,object :RoomAdapter.OnItemClickListener{
            var a = 100
            override fun onItemClick(malumotlar: MovieEntity, position: Int) {
                if (a==position) {
                    val movieEntity = MovieEntity()
                    movieEntity.icon = malumotlar.icon
                    movieEntity.headline = malumotlar.headline
                    movieEntity.name = malumotlar.name
                    movieEntity.summary = malumotlar.summary
                    appDatabase.movieDao().addUsers(movieEntity)
                    Toast.makeText(binding.root.context, "Added to list", Toast.LENGTH_SHORT).show()

                    a++
                } else {
                    appDatabase.movieDao().deleteByName(malumotlar.name!!)
                    Toast.makeText(binding.root.context, "Deleted", Toast.LENGTH_SHORT).show()
                    a=position.toString().toInt()
                }
            }

        })

        binding.rv.adapter = roomAdapter






        return binding.root
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment SavedFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            SavedFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}