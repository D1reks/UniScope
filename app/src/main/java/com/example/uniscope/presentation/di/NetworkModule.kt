package com.example.uniscope.presentation.di

import com.example.uniscope.data.remote.jsoup.JsoupWrapper
import com.example.uniscope.data.remote.parser.BelarusInstitutionParserImpl
import com.example.uniscope.domain.repository.InstitutionParser
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideJsoupWrapper(): JsoupWrapper = JsoupWrapper()

    @Provides
    @Singleton
    fun provideInstitutionParser(
        jsoupWrapper: JsoupWrapper,
        @IoDispatcher dispatcher: CoroutineDispatcher
    ): InstitutionParser = BelarusInstitutionParserImpl(jsoupWrapper, dispatcher)
}