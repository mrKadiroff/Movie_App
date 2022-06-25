package com.example.movies_app.bottomFragments

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.Glide
import com.example.movies_app.R
import com.example.movies_app.adapters.NewAdapter
import com.example.movies_app.adapters.RecentAdapter
import com.example.movies_app.bottomFragments.fragments.ChildActivity
import com.example.movies_app.databinding.FragmentFeedBinding
import com.example.movies_app.network.ApiClient
import com.example.movies_app.network.ApiSearchClient
import com.example.movies_app.network.allmovies.AllResult
import com.example.movies_app.network.allmovies.Result
import com.example.movies_app.network.omd.OmdResult
import com.example.movies_app.repository.*
import com.example.movies_app.viewmodels.MainViewModel
import com.example.movies_app.viewmodels.ViewModelFactory
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [FeedFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class FeedFragment : Fragment() {
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

    lateinit var binding: FragmentFeedBinding
    private lateinit var mainViewModel: MainViewModel
    lateinit var recentAdapter: RecentAdapter
    lateinit var newsAdapter: NewAdapter
    private val TAG = "FeedFragment"
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentFeedBinding.inflate(layoutInflater, container, false)





        setUpViewModel()
        lifecycleScope.launch {
            mainViewModel.movieInent.send(MainIntent.FetchUser)
            mainViewModel.newInent.send(NewIntent.FetchNew)
        }

        observeViewModel()
        observeNewModel()
        observeOmdModel()

        setUi()




        return binding.root
    }

    private fun observeNewModel() {
        lifecycleScope.launch {
            mainViewModel.newstate.collect {
                when (it) {
                    is NewState.Idle -> {

                    }
                    is NewState.Loading -> {

                    }
                    is NewState.Users -> {
                        Log.d(TAG, "observeNewModel: ${it.user}")
                        newsAdapter = NewAdapter(it.user.results,object :NewAdapter.OnItemClickListener{
                            override fun onItemClick(
                                malumotlar: com.example.movies_app.network.newresult.Result,
                                position: Int
                            ) {
                                val intent = Intent(binding.root.context,ChildActivity::class.java)
                                intent.putExtra("news",malumotlar)
                                intent.putExtra("new","new")
                                intent.putExtra("num2",position)
                                startActivity(intent)
                            }

                        })
                        binding.popularRv.adapter = newsAdapter
                    }

                    is NewState.Error -> {

                        Toast.makeText(binding.root.context, it.error, Toast.LENGTH_LONG).show()
                    }
                }
            }
        }
    }

    private fun observeOmdModel() {
        lifecycleScope.launch {
            mainViewModel.omdstate.collect {
                when (it) {
                    is OmdState.Idle -> {

                    }
                    is OmdState.Loading -> {

                    }
                    is OmdState.Search -> {
                        try {
                            Glide.with(binding.root.context).load(it.search.Poster).into(binding.imagea);
                        }catch (e:Exception){
                            Toast.makeText(binding.root.context, e.message, Toast.LENGTH_SHORT).show()
                        }
                        binding.movieName.text = it.search.Title
                        binding.genre.text = it.search.Actors


                    }

                    is OmdState.Error -> {

                        Toast.makeText(binding.root.context, it.error, Toast.LENGTH_LONG).show()
                    }
                }
            }
        }
    }


    private fun observeViewModel() {
        lifecycleScope.launch {
            mainViewModel.state.collect {
                when (it) {
                    is MainState.Idle -> {

                    }
                    is MainState.Loading -> {

                    }
                    is MainState.Users -> {
                        recentAdapter = RecentAdapter(it.user.results,object :RecentAdapter.OnItemClickListener{


                            override fun onItemClick(malumotlar: Result, position: Int) {
                                val intent = Intent(binding.root.context,ChildActivity::class.java)
                                intent.putExtra("recent",malumotlar)
                                intent.putExtra("rec","rec")
                                intent.putExtra("num1",position)
                                startActivity(intent)
                            }

                        })
                        binding.recentRv.adapter = recentAdapter
                    }

                    is MainState.Error -> {

                        Toast.makeText(binding.root.context, it.error, Toast.LENGTH_LONG).show()
                    }
                }
            }
        }
    }

    private fun setUpViewModel() {
        mainViewModel = ViewModelProvider(
            this,
            ViewModelFactory(
                ApiClient.apiService,
                ApiSearchClient.apiSearchService
            )
        ).get(MainViewModel::class.java)
    }

    private fun setUi() {
        binding.search.setOnClickListener {

            binding.movieplus.visibility = View.GONE
            binding.search.visibility = View.GONE
            binding.cleartexae.visibility = View.VISIBLE
            binding.editetextt.visibility = View.VISIBLE

        }

        binding.cleartexae.setOnClickListener {
            binding.movieplus.visibility = View.VISIBLE
            binding.search.visibility = View.VISIBLE
            binding.cleartexae.visibility = View.GONE
            binding.editetextt.visibility = View.GONE


            binding.recent.visibility = View.VISIBLE
            binding.recentRv.visibility = View.VISIBLE
            binding.popularRv.visibility = View.VISIBLE
            binding.cardview.visibility = View.GONE
            binding.movieName.visibility = View.GONE
            binding.genre.visibility = View.GONE



        }



        binding.editetextt.setOnEditorActionListener(TextView.OnEditorActionListener { v, actionId, event ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                Toast.makeText(binding.root.context, "Search bosildi", Toast.LENGTH_SHORT).show()
                binding.recent.visibility = View.GONE
                binding.recentRv.visibility = View.GONE
                binding.popularRv.visibility = View.GONE
                binding.cardview.visibility = View.VISIBLE
                binding.movieName.visibility = View.VISIBLE
                binding.genre.visibility = View.VISIBLE
                val toString = binding.editetextt.text.toString()
                lifecycleScope.launch {
                    mainViewModel.omdInent.send(OmdIntent.FetchOmd)
                    mainViewModel.wordim = toString
                }
                return@OnEditorActionListener true
            }
            false
        })

    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment FeedFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            FeedFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}