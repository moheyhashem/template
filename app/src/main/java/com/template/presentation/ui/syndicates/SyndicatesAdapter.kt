package com.template.presentation.ui.syndicates

import androidx.databinding.DataBindingComponent
import androidx.databinding.DataBindingUtil
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.DiffUtil
import android.view.LayoutInflater
import android.view.ViewGroup
import com.template.AppExecutors
import com.template.R
import com.template.databinding.SyndicateItemBinding
import com.template.pre.common.DataBoundListAdapter
import com.template.presentation.entities.SyndicateUI
import com.template.presentation.util.DisplayMetrics
//import com.template.presentation.ui.common.

class SyndicatesAdapter(
    private val dataBindingComponent: DataBindingComponent,
    appExecutors: AppExecutors,
    private val callback: ((SyndicateUI) -> Unit)?
) : DataBoundListAdapter<SyndicateUI, SyndicateItemBinding>(
        appExecutors = appExecutors,
        diffCallback = object : DiffUtil.ItemCallback<SyndicateUI>() {
            override fun areItemsTheSame(oldItem: SyndicateUI, newItem: SyndicateUI): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: SyndicateUI, newItem: SyndicateUI): Boolean {
                return oldItem.address == newItem.address
            }
        }
) {

    override fun createBinding(parent: ViewGroup): SyndicateItemBinding {
        val binding = DataBindingUtil
                .inflate<SyndicateItemBinding>(
                        LayoutInflater.from(parent.context),
                        R.layout.syndicate_item,
                        parent,
                        false,
                        dataBindingComponent
                )
        binding.root.setOnClickListener {
            binding.syndicate?.let {
                callback?.invoke(it)
            }
        }

        val layoutParams = ConstraintLayout.LayoutParams(DisplayMetrics.width / 3, DisplayMetrics.width / 3)
        binding.root.layoutParams = layoutParams
        return binding
    }

    override fun bind(binding: SyndicateItemBinding, item: SyndicateUI) {
        binding.syndicate = item
    }
}
