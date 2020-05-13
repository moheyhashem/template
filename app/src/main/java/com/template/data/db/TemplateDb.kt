package com.template.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.template.data.entities.ProviderData

@Database(
    entities = [ProviderData::class],
    version = 1,
    exportSchema = false
)
abstract class TemplateDb : RoomDatabase() {
    abstract fun providerDao(): ProviderDao
}
