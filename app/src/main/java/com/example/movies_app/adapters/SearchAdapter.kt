package com.example.movies_app.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.movies_app.databinding.RecentItem2Binding
import com.example.movies_app.network.searchMovies.Result

class SearchAdapter(
    var list: List<Result>,
) : RecyclerView.Adapter<SearchAdapter.Vh>() {

    inner class Vh(var malumotItemBinding: RecentItem2Binding) :
        RecyclerView.ViewHolder(malumotItemBinding.root) {

        fun onBind(malumotlar: Result, position: Int) {

            malumotItemBinding.movieName.text = malumotlar.display_title
            Glide.with(malumotItemBinding.root.context).load(malumotlar.multimedia.src).into(malumotItemBinding.imagea);

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Vh {
        return Vh(RecentItem2Binding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: Vh, position: Int) {
        holder.onBind(list[position], position)
        //dsdsdsdsdsd
    }

    override fun getItemCount(): Int = list.size



}