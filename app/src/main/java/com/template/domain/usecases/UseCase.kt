package com.template.domain.usecases

import com.template.domain.common.Transformer
import io.reactivex.Observable
import io.reactivex.ObservableTransformer
import io.reactivex.Single
import io.reactivex.SingleTransformer

abstract class UseCase<T>(private val observableTransformer: Transformer<T>) {

    abstract fun createObservable(data: Map<String, Any>? = null): Observable<T>

    fun observable(withData: Map<String, Any>? = null) = createObservable(withData).compose(observableTransformer)

}