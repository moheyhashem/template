package com.template.presentation.common

import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import android.view.View
import androidx.lifecycle.ViewModel
import javax.inject.Inject
import com.template.R
import dagger.android.support.DaggerFragment

open class BaseFragment<T : ViewModel> : DaggerFragment() {
    @Inject
    lateinit var viewModel: T

    var builder: AlertDialog.Builder? = null
    var dialog: AlertDialog? = null
    fun setupToolbar(showUp: Boolean = true, show: Boolean = true) {
        when (show) {
            true -> {
                (activity as AppCompatActivity).supportActionBar?.show()
                (activity as AppCompatActivity).supportActionBar?.setDisplayHomeAsUpEnabled(showUp)
            }
            false -> (activity as AppCompatActivity).supportActionBar?.hide()
        }
    }

    fun setToolbarTitle(title: String) {
        (activity as AppCompatActivity).supportActionBar?.title = title
    }

    fun showConnectionAlert(context: Context, retryCallback: (() -> Unit), cancelCallback: (() -> Unit)) {
        builder = AlertDialog.Builder(context)
        builder?.setTitle(getString(R.string.error))
        builder?.setMessage(getString(R.string.error_msg))
        builder?.setCancelable(false)
        builder?.setPositiveButton(getString(R.string.no_connection_retry)) { dialog, which ->
            retryCallback.invoke()
        }
        builder?.setNegativeButton(getString(R.string.no_connection_cancel)) { dialog, which ->
            cancelCallback.invoke()
        }

        if (dialog == null)
            dialog = builder?.create()

        if (!dialog?.isShowing!!)
            dialog?.show()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        (activity as AppCompatActivity).invalidateOptionsMenu()
        super.onViewCreated(view, savedInstanceState)

    }
}