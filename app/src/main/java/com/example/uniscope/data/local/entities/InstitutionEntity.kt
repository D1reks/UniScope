package com.example.uniscope.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.uniscope.domain.model.EducationalInstitution

@Entity(tableName = "institutions")
data class InstitutionEntity(
    @PrimaryKey
    val id: String,
    val name: String,
    val type: String, // "COLLEGE" or "UNIVERSITY"
    val city: String,
    val logoUrl: String?,
    val website: String
) {
    companion object {
        fun fromDomain(institution: EducationalInstitution): InstitutionEntity {
            return InstitutionEntity(
                id = institution.id,
                name = institution.name,
                type = institution.type.name,
                city = institution.city,
                logoUrl = institution.logoUrl,
                website = institution.website
            )
        }
    }
}
