package com.example.uniscope.data.local.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.uniscope.data.local.dao.InstitutionDao
import com.example.uniscope.data.local.entities.InstitutionEntity
import com.example.uniscope.data.local.entities.SpecialtyEntity

@Database(
    entities = [InstitutionEntity::class, SpecialtyEntity::class],
    version = 1,
    exportSchema = false
)
abstract class EducationDatabase : RoomDatabase() {
    abstract fun institutionDao(): InstitutionDao

    companion object {
        @Volatile
        private var INSTANCE: EducationDatabase? = null

        fun getInstance(context: Context): EducationDatabase {
            return INSTANCE ?: synchronized(this) {
                INSTANCE ?: Room.databaseBuilder(
                    context.applicationContext,
                    EducationDatabase::class.java,
                    "education_db"
                ).build().also { INSTANCE = it }
            }
        }
    }
}