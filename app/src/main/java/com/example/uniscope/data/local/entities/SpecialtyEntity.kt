package com.example.uniscope.data.local.entities

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.example.uniscope.domain.model.Specialty
import java.time.ZoneOffset

@Entity(
    tableName = "specialties",
    foreignKeys = [ForeignKey(
        entity = InstitutionEntity::class,
        parentColumns = ["id"],
        childColumns = ["institutionId"],
        onDelete = ForeignKey.CASCADE
    )]
)
data class SpecialtyEntity(
    @PrimaryKey
    val id: String,
    val institutionId: String,
    val name: String,
    val code: String,
    val currentApplications: Int,
    val availablePlaces: Int,
    val requiredScore: Double
) {
    companion object {
        fun fromDomain(specialty: Specialty, institutionId: String): SpecialtyEntity {
            return SpecialtyEntity(
                id = specialty.id,
                institutionId = institutionId,
                name = specialty.name,
                code = specialty.code,
                currentApplications = specialty.currentApplications,
                availablePlaces = specialty.availablePlaces,
                requiredScore = specialty.requiredScore
            )
        }
    }
}
