package com.example.app_filmes.view.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.app_filmes.view.model.Movie
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.app_filmes.databinding.ActivityListItemBinding
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*


class ResultAdapter(private val onClickListener: (movie: Movie) -> Unit) :
    ListAdapter<Movie, ResultAdapter.FilmeItemViewHolder>(DIFF_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FilmeItemViewHolder {
        val binding = ActivityListItemBinding.inflate(LayoutInflater.from(parent.context),
                parent, false)
        return FilmeItemViewHolder(binding, onClickListener)
    }

    override fun onBindViewHolder(holder: FilmeItemViewHolder, position: Int) {
        holder.bind(getItem(position))
    }


    inner class FilmeItemViewHolder(
        private val binding: ActivityListItemBinding,
        private val onClickListener: (movie: Movie) -> Unit,
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(filme: Movie) {
            binding.nomeFilmeItem.text = filme.title
            binding.filmeAvaliacao.rating = filme.getAvaliacao()
            binding.filmeDate.text = filme.release_date.getDateTimeFormatted()

            Glide.with(binding.root.context)
                .load(filme.getImagemCapa())
                .centerCrop()
                .into(binding.filmeItem)

            binding.root.setOnClickListener {
                onClickListener.invoke(filme)
            }
        }

        private fun String.getDateTimeFormatted(): String {
            try {

                val dateFormat = SimpleDateFormat("yyyy-MM-dd", getLocale())
                val date = dateFormat.parse(this)
                date?.let {

                    return getDateToStringFormatted(date, "dd-MM-yyyy")
                }
            } catch (e: ParseException) {
                e.localizedMessage?.let {
                    Log.d("TAG", "getDateTimeFormatted: $e")
                }
            }
            return orEmpty()
        }

        fun getDateToStringFormatted(date: Date, dateString: String): String {
            val simpleDateFormat = SimpleDateFormat(dateString, getLocale())
            return simpleDateFormat.format(date)
        }

        fun getLocale(): Locale {
            return Locale("pt", "BR")
        }

    }

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Movie>() {
            override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean {
                return oldItem.id == newItem.id
            }
            override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean {
                return oldItem == newItem
            }
        }
    }
}