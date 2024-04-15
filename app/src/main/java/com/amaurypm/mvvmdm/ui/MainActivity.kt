package com.amaurypm.mvvmdm.ui

import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.amaurypm.mvvmdm.R
import com.amaurypm.mvvmdm.data.remote.model.AnimeDto
import com.amaurypm.mvvmdm.databinding.ActivityMainBinding
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private var animes = mutableListOf<AnimeDto>()

    private val mainViewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)

        val myAdapter = AnimesAdapter(animes){
            //Aquí va el click de los elementos
        }

        binding.rv.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = myAdapter
        }

        binding.btnAdd.setOnClickListener {
            mainViewModel.getAnime()

            binding.btnAdd.isEnabled = false
            binding.pbDownload.visibility = View.VISIBLE
        }

        mainViewModel.anime.observe(this, Observer{ anime ->
            //LLega la notificación de que el contenedor ha sido actualizado

            binding.btnAdd.isEnabled = true
            binding.pbDownload.visibility = View.INVISIBLE

            animes.add(anime)

            myAdapter.notifyItemInserted(animes.size-1)
        })


    }
}