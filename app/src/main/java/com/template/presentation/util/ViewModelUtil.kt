//package com.template.presentation.util
//
//import androidx.lifecycle.ViewModel
//import androidx.lifecycle.ViewModelProvider
//
//
///**
// * Creates a one off view model factory for the given view model instance.
// */
//fun <T: ViewModel> T.createFactory(): ViewModelProvider.Factory {
//    val viewModel = this
//    return object: ViewModelProvider.Factory {
//        @Suppress("UNCHECKED_CAST")
//        override fun <T: ViewModel> create(modelClass: Class<T>): T = viewModel as T
//    }
//}