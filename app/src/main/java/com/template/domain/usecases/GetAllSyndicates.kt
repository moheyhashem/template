package com.template.domain.usecases

import com.template.domain.TemplateRepository
import com.template.domain.common.Transformer
import com.template.domain.entities.SyndicateEntity
import io.reactivex.Observable
import io.reactivex.Single
import javax.inject.Inject

class GetAllSyndicates @Inject constructor(
    transformer: Transformer<List<SyndicateEntity>>,
    private val templateRepository: TemplateRepository
) : UseCase<List<SyndicateEntity>>(transformer) {

    override fun createObservable(data: Map<String, Any>?): Observable<List<SyndicateEntity>> {
        return templateRepository.getSyndicates().toObservable()
    }
}