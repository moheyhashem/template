package com.template.presentation.di

import com.template.data.api.WebService
import com.template.data.repositories.CachedTemplateDataStore
import com.template.data.repositories.MemoryTemplateCache
import com.template.data.repositories.RemoteTemplateDataStore
import com.template.data.repositories.TemplateRepositoryImpl
import com.template.domain.TemplateCache
import com.template.domain.TemplateRepository
import dagger.Module
import dagger.Provides
import javax.inject.Named
import javax.inject.Singleton

@Module
class RepositoryModule {

    @Provides
    @Singleton
    fun provideRepository(
            api: WebService,
            @Named(DI.inMemoryCache) cache: TemplateCache
    ): TemplateRepository {

        val cachedWeatherDataStore = CachedTemplateDataStore(cache)
        val remoteWeatherDataStore = RemoteTemplateDataStore(api)
        return TemplateRepositoryImpl(cachedWeatherDataStore, remoteWeatherDataStore)
    }

    @Singleton
    @Provides
    @Named(DI.inMemoryCache)
    fun provideInMemoryCache(): TemplateCache {
        return MemoryTemplateCache()
    }
}