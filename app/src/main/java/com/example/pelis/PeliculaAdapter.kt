package com.example.pelis
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView

class PeliculaAdapter(
    private val onEditClickListener: (Pelicula) -> Unit,
    private val onDeleteClickListener: (Pelicula) -> Unit
) : ListAdapter<Pelicula, PeliculaAdapter.PeliculaViewHolder>(PeliculaDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PeliculaViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.item_pelicula, parent, false)
        return PeliculaViewHolder(view)
    }

    override fun onBindViewHolder(holder: PeliculaViewHolder, position: Int) {
        val pelicula = getItem(position)
        holder.bind(pelicula)
    }

    inner class PeliculaViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val tituloTextView: TextView = itemView.findViewById(R.id.tituloTextView)
        private val generoImageView: ImageView = itemView.findViewById(R.id.generoImageView)

        init {
            itemView.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    val pelicula = getItem(position)
                    onEditClickListener(pelicula)
                }
            }
            itemView.setOnLongClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    val pelicula = getItem(position)
                    onDeleteClickListener(pelicula)
                    return@setOnLongClickListener true
                }
                return@setOnLongClickListener false
            }
        }

        fun bind(pelicula: Pelicula) {
            tituloTextView.text = pelicula.titulo
            // Aquí debes implementar la lógica para cargar la imagen del género
            // en el ImageView generoImageView según el género de la película.
            // Puedes utilizar un recurso drawable o una URL para cargar la imagen.
            //generoImageView.setImageResource(R.drawable.ic_genero_comedia)
        }
    }

    class PeliculaDiffCallback : DiffUtil.ItemCallback<Pelicula>() {
        override fun areItemsTheSame(oldItem: Pelicula, newItem: Pelicula): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Pelicula, newItem: Pelicula): Boolean {
            return oldItem == newItem
        }
    }
}
