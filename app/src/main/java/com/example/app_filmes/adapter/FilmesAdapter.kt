package com.example.app_filmes.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.app_filmes.databinding.ActivityListItemBinding
import com.example.app_filmes.model.Filmes

class FilmesAdapter : ListAdapter<Filmes, FilmesAdapter.FilmesViewHolder>(diffCallBack) {


    class FilmesViewHolder(val binding: ActivityListItemBinding):
        RecyclerView.ViewHolder(binding.root) {
        fun bind(filmes: Filmes) {
            binding.nomeFilmeItem.text = filmes.toString()



        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FilmesViewHolder {
        return FilmesViewHolder(
            ActivityListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }


    override fun onBindViewHolder(holder: FilmesViewHolder, position: Int) {
        holder.bind(getItem(position))

    }



    companion object {

        private val diffCallBack = object : DiffUtil.ItemCallback<Filmes>() {
            override fun areItemsTheSame(oldItem: Filmes, newItem: Filmes): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: Filmes, newItem: Filmes): Boolean {
                return oldItem == newItem
            }
        }
    }

}