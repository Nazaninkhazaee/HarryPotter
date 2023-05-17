package com.nazanin.harrypotter.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.nazanin.harrypotter.database.dao.AppDatabase.Companion.getDatabase
import com.nazanin.harrypotter.domin.CharacterModel
import com.nazanin.harrypotter.repository.CharacterRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class OverviewViewModel(application: Application) : AndroidViewModel(application) {
    private val database = getDatabase(application)
    private val repository = CharacterRepository(database)

    private var viewModelJob = Job()
    private val coroutineScope = CoroutineScope(viewModelJob + Dispatchers.Main)

    private val _navigateToSelectedItems = MutableLiveData<CharacterModel?>()
    val navigateToSelectedItems: LiveData<CharacterModel?>
        get() = _navigateToSelectedItems

    var characterModel = repository.characters
    var staffModel = repository.staff
    var studentModel = repository.students
    private val _searchResults = MutableLiveData<List<CharacterModel>>()
    val searchResults: LiveData<List<CharacterModel>> get() = _searchResults


    init {
        fetchCharacter()
    }


    private fun fetchCharacter() {
        coroutineScope.launch {
            repository.fetchAllCharacters()
        }
    }

    fun displayDetails(characters: CharacterModel) {
        _navigateToSelectedItems.value = characters
    }

    fun displayDetailsComplete() {
        _navigateToSelectedItems.value = null
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }

    fun searchByName(name: String) {
        coroutineScope.launch {
            val results = repository.searchCharactersByName(name)
            _searchResults.value = results
        }
    }
}