package com.template.presentation.ui.syndicates

import com.template.presentation.entities.SyndicateUI

data class SyndicatesViewState(
    var isLoading: Boolean = true,
    var syndicates: List<SyndicateUI>? = null
)
