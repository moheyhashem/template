package com.template.presentation.ui.home

import androidx.lifecycle.MutableLiveData
import com.template.presentation.common.BaseViewModel
import com.template.presentation.common.SingleLiveEvent
import com.template.testing.OpenForTesting
import javax.inject.Inject

@OpenForTesting
class HomeViewModel @Inject constructor() : BaseViewModel() {

    var errorState: SingleLiveEvent<Throwable> = SingleLiveEvent()
    var viewState: MutableLiveData<HomeViewState> = MutableLiveData()

    init {
        viewState.value = HomeViewState()
    }
}
