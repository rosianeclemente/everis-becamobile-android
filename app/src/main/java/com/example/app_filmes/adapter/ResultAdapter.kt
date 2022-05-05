package com.example.app_filmes.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.app_filmes.databinding.ActivityListItemBinding
import com.example.app_filmes.model.Result

class ResultAdapter(
    private val context: Context,
    filmes: List<Result> = emptyList(),
    var quandoClicaNoItem: (result: Result) -> Unit = {},
) : RecyclerView.Adapter<ResultAdapter.ViewHolder>() {


    private val filmes = filmes.toMutableList()

    inner class ViewHolder(private val binding: ActivityListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(filmes: Result) {
            binding.nomeFilmeItem.text = filmes.title
            binding.caracteristicasFilmeItem.text = filmes.overview

        }

        private lateinit var result: Result

        init {
            itemView.setOnClickListener {
                if (::result.isInitialized) {
                    quandoClicaNoItem
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ResultAdapter.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ActivityListItemBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ResultAdapter.ViewHolder, position: Int) {
        val filme = filmes[position]
        holder.bind(filme)
    }

    override fun getItemCount(): Int = filmes.size

}