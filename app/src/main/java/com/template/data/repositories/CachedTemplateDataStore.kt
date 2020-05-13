package com.template.data.repositories

import com.template.domain.TemplateCache
import com.template.domain.TemplateDataStore
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
class CachedTemplateDataStore @Inject constructor(private val templateCache: TemplateCache) :
    TemplateDataStore {
    override fun getSyndicates(): Single<List<SyndicateEntity>> {
        TODO("not implemented") // To change body of created functions use File | Settings | File Templates.
    }

    fun saveSyndicates(syndicates: List<SyndicateEntity>): Single<List<SyndicateEntity>> {
        return templateCache.saveSyndicates(syndicates)
    }

    fun addFavorite(providerEntity: ProviderEntity): Single<ProviderEntity> {
        return templateCache.addFavorite(providerEntity)
    }

    fun removeFavorite(providerEntity: ProviderEntity): Single<ProviderEntity> {
        return templateCache.removeFavorite(providerEntity)
    }

    fun getFavorites(): Observable<List<ProviderEntity>> {
        return templateCache.getFavorites()
    }

    fun isEmpty(): Single<Boolean> {
        return templateCache.isEmpty()
    }
}