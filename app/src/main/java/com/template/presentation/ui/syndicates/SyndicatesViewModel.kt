package com.template.presentation.ui.syndicates

import androidx.lifecycle.MutableLiveData
import com.template.domain.usecases.GetAllSyndicates
import com.template.presentation.common.BaseViewModel
import com.template.presentation.common.SingleLiveEvent
import com.template.presentation.entities.SyndicateUI
import com.template.presentation.mappers.SyndicateEntityUIMapper
import com.template.testing.OpenForTesting
import javax.inject.Inject

@OpenForTesting
class SyndicatesViewModel @Inject constructor(private val getAllSyndicates: GetAllSyndicates) : BaseViewModel() {

    private val syndicateEntityUIMapper = SyndicateEntityUIMapper()
    var errorState: SingleLiveEvent<Throwable> = SingleLiveEvent()
    var viewState: MutableLiveData<SyndicatesViewState> = MutableLiveData()

    init {
        viewState.value = SyndicatesViewState()
    }

    fun getSyndicates() {
        viewState.value?.syndicates?.let {
            onSyndicatesReceived(it)
        } ?: addDisposable(getAllSyndicates.observable()
                .flatMapSingle {
                    it.let {
                        syndicateEntityUIMapper.observable(it)
                    }
                }.subscribe(
                        { onSyndicatesReceived(it) },
                        {
                            viewState.value = viewState.value?.copy(isLoading = false)
                            errorState.setValue(it)
                        }
                )
        )
    }

    private fun onSyndicatesReceived(syndicates: List<SyndicateUI>) {
        val newViewState = viewState.value?.copy(
                isLoading = false,
                syndicates = syndicates)
        viewState.value = newViewState
    }
}
