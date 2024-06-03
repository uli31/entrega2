
import android.app.Application
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.RatingBar
import android.widget.Spinner
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.pelis.Pelicula
import com.example.pelis.PeliculaAdapter
import com.example.pelis.R

class MainActivity : AppCompatActivity() {

    private lateinit var application: Application
    private lateinit var peliculaViewModel: PeliculaViewModel
    private var editandoPelicula: Pelicula? = null

    private lateinit var editTextTitulo: EditText
    private lateinit var spinnerGenero: Spinner
    private lateinit var editTextAño: EditText
    private lateinit var ratingBarValoracion: RatingBar
    private lateinit var buttonAgregar: Button
    private lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        application = getApplication()
        peliculaViewModel = ViewModelProvider(this).get(PeliculaViewModel::class.java)

        editTextTitulo = findViewById(R.id.editTextTitulo)
        spinnerGenero = findViewById(R.id.spinnerGenero)
        editTextAño = findViewById(R.id.editTextAño)
        ratingBarValoracion = findViewById(R.id.ratingBarValoracion)
        buttonAgregar = findViewById(R.id.buttonAgregar)
        recyclerView = findViewById(R.id.recyclerView)

        val adapter = PeliculaAdapter(
            onEditClickListener = { pelicula -> editarPelicula(pelicula) },
            onDeleteClickListener = { pelicula -> eliminarPelicula(pelicula) }
        )
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        peliculaViewModel.peliculas.observe(this, { peliculas ->
            adapter.submitList(peliculas)
        })

        buttonAgregar.setOnClickListener {
            val titulo = editTextTitulo.text.toString().trim()
            val genero = spinnerGenero.selectedItem.toString()
            val año = editTextAño.text.toString().toIntOrNull()
            val valoracion = ratingBarValoracion.rating

            if (titulo.isNotEmpty() && año != null) {
                if (editandoPelicula == null) {
                    val pelicula = Pelicula(titulo = titulo, genero = genero, año = año, valoracion = valoracion)
                    peliculaViewModel.insertPelicula(pelicula)
                } else {
                    editandoPelicula?.apply {
                        this.titulo = titulo
                        this.genero = genero
                        this.año = año
                        this.valoracion = valoracion
                    }?.let { peliculaViewModel.updatePelicula(it) }
                    editandoPelicula = null
                }
                limpiarFormulario()
            } else {
                Toast.makeText(this, "Por favor, completa todos los campos", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun limpiarFormulario() {
        editTextTitulo.text.clear()
        spinnerGenero.setSelection(0)
        editTextAño.text.clear()
        ratingBarValoracion.rating = 0f
        editandoPelicula = null
    }

    private fun editarPelicula(pelicula: Pelicula) {
        editTextTitulo.setText(pelicula.titulo)
        spinnerGenero.setSelection((spinnerGenero.adapter as ArrayAdapter<String>).getPosition(pelicula.genero))
        editTextAño.setText(pelicula.año.toString())
        ratingBarValoracion.rating = pelicula.valoracion
        editandoPelicula = pelicula
    }

    private fun eliminarPelicula(pelicula: Pelicula) {
        peliculaViewModel.deletePelicula(pelicula)
    }
}
