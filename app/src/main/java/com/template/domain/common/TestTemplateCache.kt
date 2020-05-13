package com.template.domain.common

import com.template.domain.TemplateCache
import com.template.domain.entities.ProviderEntity
import com.template.domain.entities.SyndicateEntity
import io.reactivex.Observable
import io.reactivex.Single

class TestTemplateCache : TemplateCache {
    override fun checkFavorite(providerEntity: ProviderEntity): Single<Boolean> {
        TODO("not implemented") // To change body of created functions use File | Settings | File Templates.
    }

    override fun getFavorites(): Observable<List<ProviderEntity>> {
        TODO("not implemented") // To change body of created functions use File | Settings | File Templates.
    }

    override fun addFavorite(providerEntity: ProviderEntity): Single<ProviderEntity> {
        TODO("not implemented") // To change body of created functions use File | Settings | File Templates.
    }

    override fun removeFavorite(providerEntity: ProviderEntity): Single<ProviderEntity> {
        TODO("not implemented") // To change body of created functions use File | Settings | File Templates.
    }

    override fun getSyndicates(): Observable<List<SyndicateEntity>> {
        TODO("not implemented") // To change body of created functions use File | Settings | File Templates.
    }

    override fun saveSyndicates(syndicates: List<SyndicateEntity>): Single<List<SyndicateEntity>> {
        TODO("not implemented") // To change body of created functions use File | Settings | File Templates.
    }

    override fun isEmpty(): Single<Boolean> {
        TODO("not implemented") // To change body of created functions use File | Settings | File Templates.
    }

//    private val weathers: HashMap<Int, WeatherEntity> = HashMap()

    override fun clear() {
//        weathers.clear()
    }
}
