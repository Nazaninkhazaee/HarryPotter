package com.nazanin.harrypotter.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.nazanin.harrypotter.domin.CharacterModel

class DetailViewModel(characters: CharacterModel, app: Application) : AndroidViewModel(app) {
    private val _selectedCharacter = MutableLiveData<CharacterModel>()

    val selectedCharacter: LiveData<CharacterModel>
        get() = _selectedCharacter

    init {
        _selectedCharacter.value = characters
    }
}