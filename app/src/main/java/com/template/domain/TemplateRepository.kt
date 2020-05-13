package com.template.domain

import com.template.domain.entities.SyndicateEntity
import io.reactivex.Observable
import io.reactivex.Single

interface TemplateRepository {
    fun getSyndicates(): Single<List<SyndicateEntity>>
}