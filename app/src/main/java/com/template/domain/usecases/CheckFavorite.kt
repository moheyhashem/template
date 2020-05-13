package com.template.domain.usecases

import com.template.domain.TemplateCache
import com.template.domain.common.Transformer
import com.template.domain.common.TransformerSingle
import com.template.domain.entities.ProviderEntity
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.SingleTransformer
import javax.inject.Inject

class CheckFavorite @Inject constructor(
    transformer: TransformerSingle<Boolean>,
    private val neqabtyCache: TemplateCache
) : SingleUseCase<Boolean>(transformer) {


    companion object {
        private const val PARAM_ENTITY = "param:entity"
    }

    fun CheckFavorite(providerEntity: ProviderEntity): Single<Boolean> {
        val data = HashMap<String, ProviderEntity>()
        data[CheckFavorite.PARAM_ENTITY] = providerEntity
        return createSingle(data)
    }

    override fun createSingle(data: Map<String, Any>?): Single<Boolean> {
        val providerEntity = data?.get(CheckFavorite.PARAM_ENTITY) as ProviderEntity
        return neqabtyCache.checkFavorite(providerEntity)
    }
}