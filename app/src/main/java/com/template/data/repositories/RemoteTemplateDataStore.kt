package com.template.data.repositories

import com.template.data.api.WebService
import com.template.domain.TemplateDataStore
import com.template.domain.entities.SyndicateEntity
import com.template.testing.OpenForTesting
import io.reactivex.Observable
import io.reactivex.Single
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
@OpenForTesting
class RemoteTemplateDataStore @Inject constructor(private val api: WebService) : TemplateDataStore {

    private val syndicateDataEntityMapper = com.template.data.mappers.SyndicateDataEntityMapper()

    override fun getSyndicates(): Single<List<SyndicateEntity>> {
        return api.getAllSyndicates().map { results ->
            results.data?.map { syndicateDataEntityMapper.mapFrom(it) }
        }
    }
}