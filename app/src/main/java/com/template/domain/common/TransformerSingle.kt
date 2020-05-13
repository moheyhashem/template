package com.template.domain.common

import io.reactivex.SingleTransformer

abstract class TransformerSingle<T> : SingleTransformer<T, T>