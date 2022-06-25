package com.example.movies_app.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.movies_app.databinding.RecentItemBinding
import com.example.movies_app.network.allmovies.Result


class RecentAdapter(
    var list: List<com.example.movies_app.network.allmovies.Result>, var onItemClickListener: OnItemClickListener
) : RecyclerView.Adapter<RecentAdapter.Vh>() {

    inner class Vh(var malumotItemBinding: RecentItemBinding) :
        RecyclerView.ViewHolder(malumotItemBinding.root) {

        fun onBind(malumotlar: Result, position: Int) {

            malumotItemBinding.movieName.text = malumotlar.display_title
            malumotItemBinding.genre.text = malumotlar.headline

            malumotItemBinding.root.setOnClickListener {
                onItemClickListener.onItemClick(malumotlar,adapterPosition)
            }

            try {
                Glide.with(malumotItemBinding.root.context).load(malumotlar.multimedia.src).into(malumotItemBinding.imagea);
            }catch (e:Exception){
                Toast.makeText(malumotItemBinding.root.context, e.message, Toast.LENGTH_SHORT).show()
            }



        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Vh {
        return Vh(RecentItemBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: Vh, position: Int) {
        holder.onBind(list[position], position)
        //dsdsdsdsdsdsdfdf
    }

    override fun getItemCount(): Int = list.size

    interface OnItemClickListener{
        fun onItemClick(malumotlar: Result,position: Int)
    }

}