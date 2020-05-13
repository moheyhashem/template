package com.template.domain.usecases

import com.template.domain.TemplateCache
import com.template.domain.common.Transformer
import com.template.domain.common.TransformerSingle
import com.template.domain.entities.ProviderEntity
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.SingleTransformer
import javax.inject.Inject

class AddFavorite @Inject constructor(
    transformer: TransformerSingle<ProviderEntity>,
    private val neqabtyCache: TemplateCache
) : SingleUseCase<ProviderEntity>(transformer) {

    companion object {
        private const val PARAM_ENTITY = "param:entity"
    }

    fun addFavorite(providerEntity: ProviderEntity): Single<ProviderEntity> {
        val data = HashMap<String, ProviderEntity>()
        data[AddFavorite.PARAM_ENTITY] = providerEntity
        return single(data)
    }

    override fun createSingle(data: Map<String, Any>?): Single<ProviderEntity> {
        val providerEntity = data?.get(AddFavorite.PARAM_ENTITY) as ProviderEntity
        return neqabtyCache.addFavorite(providerEntity)
    }
}