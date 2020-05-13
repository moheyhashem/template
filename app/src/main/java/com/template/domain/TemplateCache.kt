package com.template.domain

import com.template.domain.entities.ProviderEntity
import com.template.domain.entities.SyndicateEntity
import io.reactivex.Flowable
import io.reactivex.Observable
import io.reactivex.Single

interface TemplateCache {
    fun getSyndicates(): Observable<List<SyndicateEntity>>
    fun saveSyndicates(syndicates: List<SyndicateEntity>): Single<List<SyndicateEntity>>
    fun getFavorites(): Observable<List<ProviderEntity>>
    fun addFavorite(providerEntity: ProviderEntity): Single<ProviderEntity>
    fun removeFavorite(providerEntity: ProviderEntity): Single<ProviderEntity>
    fun checkFavorite(providerEntity: ProviderEntity): Single<Boolean>

    fun isEmpty(): Single<Boolean>
    fun clear()
}