package com.template.presentation.di

import android.content.Context
import com.template.MyApp
import dagger.Module
import dagger.Provides

@Module
class AppModule {
    @Provides
    fun provideContext(application: MyApp): Context = application.applicationContext
}
