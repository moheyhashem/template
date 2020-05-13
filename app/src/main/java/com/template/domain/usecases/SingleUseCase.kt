package com.template.domain.usecases

import com.template.domain.common.TransformerSingle
import io.reactivex.Single

abstract class SingleUseCase<T>(private val singleTransformer: TransformerSingle<T>) {

    abstract fun createSingle(data: Map<String, Any>? = null): Single<T>

    fun single(withData: Map<String, Any>? = null) = createSingle(withData).compose(singleTransformer)

}