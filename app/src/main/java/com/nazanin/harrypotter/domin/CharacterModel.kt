package com.nazanin.harrypotter.domin

import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.android.parcel.Parcelize

@Parcelize
data class CharacterModel(
    val id: String = "",
    val name: String = "",
    val species: String = "",
    val gender: String = "",
    val house: String = "",
    val dateOfBirth: String = "",
    val isWizard: Boolean = false,
    val patronus: String = "",
    val isStudent: Boolean = false,
    val isStaff: Boolean = false,
    val actor: String = "",
    val isAlive: Boolean = false,
    val image: String = ""
) :
    Parcelable {

}
