package com.example.app_filmes.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import com.example.app_filmes.model.Result
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.app_filmes.databinding.ActivityListItemBinding
import com.example.app_filmes.model.Filmes

class ResultAdapt(val onClickListener: (dogId: Int) -> Unit) : ListAdapter<Result, ResultAdapt.FilmeItemViewHolder>(DIFF_CALLBACK) {



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FilmeItemViewHolder {
        val binding =
            ActivityListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return FilmeItemViewHolder(binding, onClickListener)
    }

    override fun onBindViewHolder(holder: FilmeItemViewHolder, position: Int) {
        holder.bind(getItem(position))
    }



   inner class FilmeItemViewHolder(
        private val binding: ActivityListItemBinding,
        private val onClickListener: ((filmesId: Int) -> Unit)?,
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(filme: Result) {
            binding.nomeFilmeItem.text = filme.title
            binding.caracteristicasFilmeItem.text = filme.overview

            Glide.with(binding.root.context)
                .load(filme.getImagemCapa()) //precisa passar url?
                .centerCrop()
                .into(binding.filmeItem)

            binding.root.setOnClickListener {
                onClickListener?.invoke((filme.id))
            }
        }

    }

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Result>() {
            override fun areItemsTheSame(oldItem: Result, newItem: Result): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Result, newItem: Result): Boolean {
                return oldItem == newItem
            }

        }
    }


}