package com.nazanin.harrypotter.database.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.nazanin.harrypotter.domin.CharacterModel
import com.nazanin.harrypotter.network.model.NetworkCharacter

@Entity(tableName = "character")
data class CharacterEntity constructor(
    @PrimaryKey val id: String = "",
    @ColumnInfo(defaultValue = "") val name: String,
    @ColumnInfo(defaultValue = "")val species: String,
    @ColumnInfo(defaultValue = "") val gender: String,
    @ColumnInfo(defaultValue = "")val house: String,
    @ColumnInfo(defaultValue = "") val dateOfBirth: String ,
    @ColumnInfo(name = "is_wizard", defaultValue = "false") val isWizard: Boolean,
    @ColumnInfo(defaultValue = "") val patronus: String,
    @ColumnInfo(name = "is_student", defaultValue = "false") val isStudent: Boolean,
    @ColumnInfo(name = "is_staff", defaultValue = "false") val isStaff: Boolean,
    @ColumnInfo(defaultValue = "")val actor: String,
    @ColumnInfo(name = "is_alive", defaultValue = "false") val isAlive: Boolean,
    @ColumnInfo(defaultValue = "") val image: String
)

fun List<CharacterEntity>.asDomainModel(): List<CharacterModel> {
    return map {
        CharacterModel(
            id = it.id.orEmpty(),
            name = it.name.orEmpty(),
            species = it.species.orEmpty(),
            gender = it.gender.orEmpty(),
            house = it.house.orEmpty(),
            dateOfBirth = it.dateOfBirth.orEmpty(),
            isWizard = it.isWizard ?: false,
            patronus = it.patronus.orEmpty(),
            isStudent = it.isStudent ?: false,
            isStaff = it.isStaff ?: false,
            actor = it.actor.orEmpty(),
            isAlive = it.isAlive ?: false,
            image = it.image.orEmpty()
        )
    }
}
