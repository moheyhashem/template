package com.template.domain.usecases

import com.template.domain.TemplateCache
import com.template.domain.common.Transformer
import com.template.domain.entities.ProviderEntity
import io.reactivex.Observable
import io.reactivex.Single
import javax.inject.Inject

class GetFavorites @Inject constructor(
    transformer: Transformer<List<ProviderEntity>>,
    private val neqabtyCache: TemplateCache
) : UseCase<List<ProviderEntity>>(transformer) {

    override fun createObservable(data: Map<String, Any>?): Observable<List<ProviderEntity>> {
        return neqabtyCache.getFavorites()
    }
}