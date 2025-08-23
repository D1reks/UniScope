package com.example.uniscope.presentation.di

import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import dagger.Provides
import com.example.uniscope.data.local.db.EducationDatabase
import android.content.Context
import javax.inject.Singleton
import com.example.uniscope.data.local.dao.InstitutionDao

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideEducationDatabase(@ApplicationContext context: Context): EducationDatabase {
        return EducationDatabase.getInstance(context)
    }

    @Provides
    @Singleton
    fun provideInstitutionDao(database: EducationDatabase): InstitutionDao {
        return database.institutionDao()
    }
}