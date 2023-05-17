package com.nazanin.harrypotter.network.model


import com.nazanin.harrypotter.database.model.CharacterEntity
import com.nazanin.harrypotter.domin.CharacterModel
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class NetworkCharacter(
    @Json(name = "id")
    val id: String?,
    @Json(name = "name")
    val name: String?,
    @Json(name = "species")
    val species: String?,
    @Json(name = "gender")
    val gender: String?,
    @Json(name = "house")
    val house: String?,
    @Json(name = "dateOfBirth")
    val dateOfBirth: String?,
    @Json(name = "wizard")
    val isWizard: Boolean?,
    @Json(name = "patronus")
    val patronus: String?,
    @Json(name = "hogwartsStudent")
    val isStudent: Boolean?,
    @Json(name = "hogwartsStaff")
    val isStaff: Boolean?,
    @Json(name = "actor")
    val actor: String?,
    @Json(name = "alive")
    val isAlive: Boolean?,
    @Json(name = "image")
    val image: String?
)

fun List<NetworkCharacter>.asDomainModel(): List<CharacterModel> {
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

fun List<NetworkCharacter>.asDatabaseModel(): Array<CharacterEntity> {
    return map {
        CharacterEntity(
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
    }.toTypedArray()
}






