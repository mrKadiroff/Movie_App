package com.example.movies_app.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.movies_app.databinding.RecentItem2Binding
import com.example.movies_app.databinding.RecentItemBinding
import com.example.movies_app.network.allmovies.Result
import com.example.movies_app.room.MovieEntity


class RoomAdapter(
    var list: List<MovieEntity>, var onItemClickListener: OnItemClickListener
) : RecyclerView.Adapter<RoomAdapter.Vh>() {

    inner class Vh(var malumotItemBinding: RecentItem2Binding) :
        RecyclerView.ViewHolder(malumotItemBinding.root) {

        fun onBind(malumotlar: MovieEntity, position: Int) {

            malumotItemBinding.movieName.text = malumotlar.name
            malumotItemBinding.genre.text = malumotlar.headline

            malumotItemBinding.book.setOnClickListener {
                onItemClickListener.onItemClick(malumotlar,adapterPosition)
            }

            try {
                Glide.with(malumotItemBinding.root.context).load(malumotlar.icon).into(malumotItemBinding.imagea);
            }catch (e:Exception){
                Toast.makeText(malumotItemBinding.root.context, e.message, Toast.LENGTH_SHORT).show()
            }



        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Vh {
        return Vh(RecentItem2Binding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: Vh, position: Int) {
        holder.onBind(list[position], position)
        //dsdsdsdsdsdsdfdf
    }

    override fun getItemCount(): Int = list.size

    interface OnItemClickListener{
        fun onItemClick(malumotlar: MovieEntity,position: Int)
    }

}