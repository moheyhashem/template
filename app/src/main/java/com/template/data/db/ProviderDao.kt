package com.template.data.db

import androidx.room.*
import com.template.data.entities.ProviderData
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Observable
import io.reactivex.Single
import java.util.*

/**
 * Interface for database access for User related operations.
 */
@Dao
interface ProviderDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveProvider(providerData: ProviderData): Completable

    @Query("SELECT * FROM providerdata")
    fun getProviders(): Observable<List<ProviderData>>

    @Query("SELECT * FROM providerdata WHERE id = :providerID")
    fun isFavorite(providerID: Int): Observable<List<ProviderData>>

    @Delete
    fun removeProvider(providerData: ProviderData): Completable
}
