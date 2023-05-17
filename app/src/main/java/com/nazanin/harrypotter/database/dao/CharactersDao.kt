package com.nazanin.harrypotter.database.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.nazanin.harrypotter.database.model.CharacterEntity
import com.nazanin.harrypotter.domin.CharacterModel
import kotlinx.coroutines.Deferred

@Dao
interface CharactersDao {
    @Query("SELECT * FROM character ORDER BY house DESC")
    fun getAllCharacters(): LiveData<List<CharacterEntity>>

    @Query("SELECT * FROM character WHERE is_student = 1 ORDER BY house DESC")
    fun getStudents(): LiveData<List<CharacterEntity>>

    @Query("SELECT * FROM character WHERE is_staff = 1 ORDER BY house DESC")
    fun getStaff(): LiveData<List<CharacterEntity>>

    @Query("SELECT * FROM character WHERE name LIKE '%' || :name || '%' ORDER BY name")
    fun searchCharacterByName(name: String): List<CharacterEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(vararg characters: CharacterEntity)

}