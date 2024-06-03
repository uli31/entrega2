import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.pelis.Pelicula
import com.example.pelis.PeliculaRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class PeliculaViewModel(application: Application) : AndroidViewModel(application) {
    private val repository: PeliculaRepository
    val peliculas: LiveData<List<Pelicula>>

    init {
        val database = PeliculaDatabase.getDatabase(application)
        val peliculaDao = database.peliculaDao()

        if (peliculaDao != null) {
            repository = PeliculaRepository(peliculaDao)
            peliculas = repository.peliculas
        } else {
            throw RuntimeException("PeliculaDao instance is null")
        }
    }


    fun insertPelicula(pelicula: Pelicula) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.insertPelicula(pelicula)
        }
    }

    fun updatePelicula(pelicula: Pelicula) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.updatePelicula(pelicula)
        }
    }

    fun deletePelicula(pelicula: Pelicula) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deletePelicula(pelicula)
        }
    }
}
