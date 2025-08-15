package com.example.uniscope.presentation.di

import jakarta.inject.Qualifier

annotation class DispatcherAnnotations()
{
    @Qualifier
    @Retention(AnnotationRetention.RUNTIME)
    annotation class IoDispatcher
}
