package com.example.dreamcar.localdatabase

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.dreamcar.model.Personaje

@Database(entities = [Personaje::class], version = 1)
abstract class DragonDatabase: RoomDatabase() {

    abstract fun personajeDao(): PersonajeDAO

    companion object {
        @Volatile
        private var Instance: DragonDatabase? = null
        fun getDatabase(context: Context): DragonDatabase {
            // if the Instance is not null, return it, otherwise create a new database instance.
            return Instance ?: synchronized(this) {
                Room.databaseBuilder(context, DragonDatabase::class.java, "dragonball_database")
                    .build()
                    .also { Instance = it }
            }
        }
    }

}
