import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.pelis.Pelicula

@Database(entities = [Pelicula::class], version = 1, exportSchema = false)
abstract class PeliculaDatabase : RoomDatabase() {

    abstract fun peliculaDao(): PeliculaDao

    companion object {
        @Volatile
        private var INSTANCE: PeliculaDatabase? = null

        fun getDatabase(context: Context): PeliculaDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = INSTANCE
                if (instance != null) {
                    instance
                } else {
                    val newInstance = Room.databaseBuilder(
                        context.applicationContext,
                        PeliculaDatabase::class.java,
                        "pelicula_database"
                    ).build()
                    INSTANCE = newInstance
                    newInstance
                }
            }
        }
    }

}
