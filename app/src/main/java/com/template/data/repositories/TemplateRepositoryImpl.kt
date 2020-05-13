package com.template.data.repositories

import com.template.domain.TemplateRepository
import com.template.domain.entities.ProviderEntity
import com.template.domain.entities.SyndicateEntity
import com.template.testing.OpenForTesting
import io.reactivex.Flowable
import io.reactivex.Observable
import io.reactivex.Single
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
@OpenForTesting
class TemplateRepositoryImpl @Inject constructor(
    private val cachedDataStore: CachedTemplateDataStore,
    private val remoteDataStore: RemoteTemplateDataStore
) : TemplateRepository {
    fun saveSubSyndicates(subSyndicates: List<SyndicateEntity>): Single<List<SyndicateEntity>> {
        return cachedDataStore.saveSyndicates(subSyndicates)
    }

    override fun getSyndicates(): Single<List<SyndicateEntity>> {

        return remoteDataStore.getSyndicates()
    }

    fun saveSyndicates(syndicatesList: List<SyndicateEntity>): Single<List<SyndicateEntity>> {
        return cachedDataStore.saveSyndicates(syndicatesList)
    }

    fun addFavorite(providerEntity: ProviderEntity): Single<ProviderEntity> {
        return cachedDataStore.addFavorite(providerEntity)
    }

    fun removeFavorite(providerEntity: ProviderEntity): Single<ProviderEntity> {
        return cachedDataStore.removeFavorite(providerEntity)
    }

    fun getFavorites(): Observable<List<ProviderEntity>> {
        return cachedDataStore.getFavorites()
    }
}