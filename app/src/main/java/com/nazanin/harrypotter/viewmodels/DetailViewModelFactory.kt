package com.nazanin.harrypotter.viewmodels

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.nazanin.harrypotter.domin.CharacterModel

class DetailViewModelFactory(
    private val character: CharacterModel,
    private val application: Application
) : ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(DetailViewModel::class.java)) {
            return DetailViewModel(character, application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}