package com.nazanin.harrypotter.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.map
import com.nazanin.harrypotter.database.dao.AppDatabase
import com.nazanin.harrypotter.database.model.asDomainModel
import com.nazanin.harrypotter.domin.CharacterModel
import com.nazanin.harrypotter.network.Network
import com.nazanin.harrypotter.network.model.asDatabaseModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class CharacterRepository(private val database: AppDatabase) {

    val characters: LiveData<List<CharacterModel>> =
        database.charactersDao().getAllCharacters().map() {
            it.asDomainModel()
        }

    val students: LiveData<List<CharacterModel>> =
        database.charactersDao().getStudents().map() {
            it.asDomainModel()
        }

    val staff: LiveData<List<CharacterModel>> =
        database.charactersDao().getStaff().map() {
            it.asDomainModel()
        }

    suspend fun fetchAllCharacters() {
        withContext(Dispatchers.IO) {
            val networkCharacters = Network.request.getCharacters().await()
            database.charactersDao().insertAll(*networkCharacters.asDatabaseModel())
        }
    }

//    suspend fun searchCharactersByName(name: String): List<CharacterModel> {
//        return withContext(Dispatchers.IO) {
//            database.charactersDao().searchCharacterByName(name).asDomainModel()
//        }
//    }

    suspend fun searchCharactersByName(name: String): List<CharacterModel> = withContext(Dispatchers.IO) {
        database.charactersDao().searchCharacterByName(name).asDomainModel()
    }



}