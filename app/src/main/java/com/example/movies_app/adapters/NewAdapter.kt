package com.example.movies_app.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.movies_app.databinding.PopularItemBinding
import com.example.movies_app.databinding.RecentItemBinding
import com.example.movies_app.network.allmovies.Result


class NewAdapter(
    var list: List<com.example.movies_app.network.newresult.Result>,var onItemClickListener: OnItemClickListener
) : RecyclerView.Adapter<NewAdapter.Vh>() {

    inner class Vh(var malumotItemBinding:PopularItemBinding) :
        RecyclerView.ViewHolder(malumotItemBinding.root) {

        fun onBind(malumotlar: com.example.movies_app.network.newresult.Result, position: Int) {

            malumotItemBinding.name.text = malumotlar.display_title
            malumotItemBinding.root.setOnClickListener {
                onItemClickListener.onItemClick(malumotlar,adapterPosition)
            }

            try {
                Glide.with(malumotItemBinding.root.context).load(malumotlar.multimedia.src).into(malumotItemBinding.rasm);
            }catch (e:Exception){
                Toast.makeText(malumotItemBinding.root.context, e.message, Toast.LENGTH_SHORT).show()
            }



        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Vh {
        return Vh(PopularItemBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: Vh, position: Int) {
        holder.onBind(list[position], position)
        //dsdsdsdsdsdsdfdf
    }

    override fun getItemCount(): Int = list.size

    interface OnItemClickListener{
        fun onItemClick(malumotlar: com.example.movies_app.network.newresult.Result,position: Int)
    }


}