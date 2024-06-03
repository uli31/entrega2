
import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.pelis.Pelicula

@Dao
interface PeliculaDao {
    @Query("SELECT * FROM peliculas ORDER BY id DESC")
    fun getPeliculas(): LiveData<List<Pelicula>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPelicula(pelicula: Pelicula)

    @Update
    suspend fun updatePelicula(pelicula: Pelicula)

    @Delete
    suspend fun deletePelicula(pelicula: Pelicula)
}
