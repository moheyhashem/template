package com.template.presentation.di

import com.template.domain.TemplateCache
import com.template.domain.TemplateRepository
import com.template.domain.usecases.AddFavorite
import com.template.domain.usecases.CheckFavorite
import com.template.domain.usecases.GetAllSyndicates
import com.template.domain.usecases.GetFavorites
import com.template.presentation.common.ASyncTransformer
import com.template.presentation.common.SingleASyncTransformer
import dagger.Module
import dagger.Provides
import javax.inject.Named
import javax.inject.Singleton

@Module
class UseCasesModule {

    @Singleton
    @Provides
    fun provideGetAllSyndicates(templateRepository: TemplateRepository): GetAllSyndicates {
        return GetAllSyndicates(ASyncTransformer(), templateRepository)
    }

    @Singleton
    @Provides
    fun provideAddFavorite(@Named(DI.roomCache) templateCache: TemplateCache): AddFavorite {
        return AddFavorite(SingleASyncTransformer(), templateCache)
    }

    @Singleton
    @Provides
    fun provideGetFavorites(@Named(DI.roomCache) templateCache: TemplateCache): GetFavorites {
        return GetFavorites(ASyncTransformer(), templateCache)
    }

    @Singleton
    @Provides
    fun provideCheckFavorite(@Named(DI.roomCache) templateCache: TemplateCache): CheckFavorite {
        return CheckFavorite(SingleASyncTransformer(), templateCache)
    }
}