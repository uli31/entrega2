package com.amaurypm.mvvmdm.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.amaurypm.mvvmdm.data.remote.model.AnimeDto
import com.amaurypm.mvvmdm.databinding.AnimesElementBinding

class AnimesAdapter(
    private val animes: MutableList<AnimeDto>,
    private val onAnimeClicked: (AnimeDto) -> Unit  //para manejar los clicks
): RecyclerView.Adapter<AnimeViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AnimeViewHolder {
        val binding = AnimesElementBinding.inflate(LayoutInflater.from(parent.context))
        return AnimeViewHolder(binding)
    }

    override fun getItemCount(): Int = animes.size

    override fun onBindViewHolder(holder: AnimeViewHolder, position: Int) {

        val anime = animes[position]

        holder.bind(anime)

        //Click del elemento
        holder.itemView.setOnClickListener {
            onAnimeClicked(anime)
        }

    }

}