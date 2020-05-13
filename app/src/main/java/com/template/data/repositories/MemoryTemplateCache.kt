package com.template.data.repositories

import com.template.domain.TemplateCache
import com.template.domain.entities.ProviderEntity
import com.template.domain.entities.SyndicateEntity
import com.template.testing.OpenForTesting
import io.reactivex.Observable
import io.reactivex.Single
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
@OpenForTesting
class MemoryTemplateCache @Inject constructor() : TemplateCache {
    override fun checkFavorite(providerEntity: ProviderEntity): Single<Boolean> {
        TODO("not implemented") // To change body of created functions use File | Settings | File Templates.
    }

    override fun addFavorite(providerEntity: ProviderEntity): Single<ProviderEntity> {
        TODO("not implemented") // To change body of created functions use File | Settings | File Templates.
    }

    override fun removeFavorite(providerEntity: ProviderEntity): Single<ProviderEntity> {
        TODO("not implemented") // To change body of created functions use File | Settings | File Templates.
    }

    override fun getFavorites(): Observable<List<ProviderEntity>> {
        TODO("not implemented") // To change body of created functions use File | Settings | File Templates.
    }

    override fun getSyndicates(): Observable<List<SyndicateEntity>> {
        TODO("not implemented") // To change body of created functions use File | Settings | File Templates.
    }

    override fun saveSyndicates(syndicates: List<SyndicateEntity>): Single<List<SyndicateEntity>> {
        TODO("not implemented") // To change body of created functions use File | Settings | File Templates.
    }

    override fun isEmpty(): Single<Boolean> {
        return Single.just(true)
    }

    override fun clear() {
//        historyList.clear()
    }
}
