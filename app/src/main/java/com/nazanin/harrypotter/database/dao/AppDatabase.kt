package com.nazanin.harrypotter.database.dao

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.nazanin.harrypotter.database.model.CharacterEntity

@Database(entities = [CharacterEntity::class], version = 1, exportSchema = false)
abstract class AppDatabase: RoomDatabase() {
    abstract fun charactersDao(): CharactersDao


    companion object {

        @Volatile
        private var appDatabase: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            if (appDatabase != null){
                return appDatabase!!
            }

            appDatabase = Room.databaseBuilder(
                context.applicationContext,
                AppDatabase::class.java,
                "hp_database"
            )
                .fallbackToDestructiveMigration()
                .build()

            return appDatabase!!
        }
    }
}

