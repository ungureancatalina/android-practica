package com.example.moviescompose.data

import android.content.Context
import androidx.room.*
import kotlinx.coroutines.flow.Flow

// entitate Room ce descrie o zi de vreme
@Entity(tableName = "weather_day")
data class WeatherDayEntity(

    // data in format ISO (cheie primara)
    @PrimaryKey val dateIso: String,
    val weatherCode: Int,
    val tempMaxC: Double,
    val tempMinC: Double
)


// Data Access Object - interogarile pentru baza de date
@Dao
interface WeatherDao {

    // returneaza toate inregistrarile ordonate dupa data
    @Query("SELECT * FROM weather_day ORDER BY dateIso ASC")
    fun observeAllOrdered(): Flow<List<WeatherDayEntity>>

    // sterge toate inregistrarile
    @Query("DELETE FROM weather_day")
    suspend fun clearAll()

    // insereaza o lista de inregistrari
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(items: List<WeatherDayEntity>)

    // tranzactie: sterge tot si insereaza noile date
    @Transaction
    suspend fun replaceAll(items: List<WeatherDayEntity>) {
        clearAll()
        insertAll(items)
    }
}


// clasa RoomDatabase ce gestioneaza baza de date
@Database(
    entities = [WeatherDayEntity::class],
    version = 1,
    exportSchema = false
)
abstract class WeatherDatabase : RoomDatabase() {
    abstract fun weatherDao(): WeatherDao

    companion object {
        // metoda factory pentru a construi baza de date
        fun build(context: Context): WeatherDatabase =
            Room.databaseBuilder(
                context,
                WeatherDatabase::class.java,
                "weather.db"
            ).build()
    }
}
