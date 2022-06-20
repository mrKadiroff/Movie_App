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
import com.example.movies_app.R
import com.example.movies_app.bottomFragments.fragments.ChildActivity
import com.example.movies_app.databinding.FragmentFeedBinding
import com.example.movies_app.network.ApiClient
import com.example.movies_app.network.allmovies.AllResult
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
    private val TAG = "FeedFragment"
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentFeedBinding.inflate(layoutInflater, container, false)


        setUi()

        ApiClient.apiService().getAllMovies("beKOJAq1sjYHYp2raykgNMvjzHt4npjr").enqueue(object:
        Callback<AllResult>{
            override fun onResponse(call: Call<AllResult>, response: Response<AllResult>) {
                if (response.isSuccessful) {
                    val body = response.body()
                    Log.d(TAG, "onResponse: $body")
                }
            }

            override fun onFailure(call: Call<AllResult>, t: Throwable) {
                Log.d(TAG, "onFailure: ${t.message}")
            }

        })


        return binding.root
    }

    private fun setUi() {
        binding.search.setOnClickListener {
            binding.toolbar.visibility = View.GONE
            binding.edittoolbar.visibility = View.VISIBLE
        }

        binding.clear.setOnClickListener {
            binding.toolbar.visibility = View.VISIBLE
            binding.edittoolbar.visibility = View.GONE
        }


        binding.editetext.setOnEditorActionListener(TextView.OnEditorActionListener { v, actionId, event ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                Toast.makeText(binding.root.context, "Search bosildi", Toast.LENGTH_SHORT).show()
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