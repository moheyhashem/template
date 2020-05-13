package com.template.presentation.di

import android.app.Application
import androidx.room.Room
import com.template.data.db.ProviderDao
import com.template.data.db.RoomHistoryCache
import com.template.data.db.TemplateDb
import com.template.data.mappers.ProviderDataEntityMapper
import com.template.data.mappers.ProviderEntityDataMapper
import com.template.domain.TemplateCache
import dagger.Module
import dagger.Provides
import javax.inject.Named
import javax.inject.Singleton

@Module
class DatabaseModule {

    @Singleton
    @Provides
    fun provideDb(app: Application): TemplateDb {
        return Room
                .databaseBuilder(app, TemplateDb::class.java, "template.db")
                .fallbackToDestructiveMigration().allowMainThreadQueries()
                .build()
    }

    @Singleton
    @Provides
    fun provideProviderDao(db: TemplateDb): ProviderDao {
        return db.providerDao()
    }

    @Singleton
    @Provides
    @Named(DI.roomCache)
    fun provideRoomCache(db: TemplateDb): TemplateCache {
        return RoomHistoryCache(db, ProviderDataEntityMapper(), ProviderEntityDataMapper())
    }
}