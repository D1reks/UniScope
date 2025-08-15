package com.example.uniscope.presentation.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object DispatcherModule {
    @Provides
    @DispatcherAnnotations.IoDispatcher
    fun providesIoDispatcher(): CoroutineDispatcher = Dispatchers.IO
}