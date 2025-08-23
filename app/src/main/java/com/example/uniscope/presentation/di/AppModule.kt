package com.example.uniscope.presentation.di

import com.example.uniscope.domain.repository.Implementation.InstitutionRepositoryImpl
import com.example.uniscope.domain.repository.InstitutionParser
import com.example.uniscope.domain.repository.InstitutionRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Singleton
import dagger.hilt.components.SingletonComponent
import com.example.uniscope.data.local.dao.InstitutionDao
import com.example.uniscope.presentation.di.DispatcherAnnotations.IoDispatcher

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideRepository(
        parser: InstitutionParser,
        dao: InstitutionDao,
        @IoDispatcher dispatcher: CoroutineDispatcher
    ): InstitutionRepository = InstitutionRepositoryImpl(parser, dao, dispatcher)
}