package com.template.presentation.ui.syndicates

import androidx.lifecycle.Observer
import android.content.Intent
import androidx.databinding.DataBindingComponent
import androidx.databinding.DataBindingUtil
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.template.AppExecutors
import com.template.R
import com.template.databinding.SyndicatesFragmentBinding
import com.template.presentation.binding.FragmentDataBindingComponent
import com.template.presentation.common.BaseFragment
import com.template.presentation.util.PreferencesHelper
import com.template.presentation.util.autoCleared
import com.template.testing.OpenForTesting
import javax.inject.Inject

@OpenForTesting
class SyndicatesFragment : BaseFragment<SyndicatesViewModel>() {

    var dataBindingComponent: DataBindingComponent = FragmentDataBindingComponent(this)

    var binding by autoCleared<SyndicatesFragmentBinding>()
    private var adapter by autoCleared<SyndicatesAdapter>()

    @Inject
    lateinit var appExecutors: AppExecutors

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        setupToolbar(false)
        binding = DataBindingUtil.inflate(
                inflater,
                R.layout.syndicates_fragment,
                container,
                false,
                dataBindingComponent
        )

        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
//        viewModel = ViewModelProviders.of(this, viewModelFactory)
//                .get(SyndicatesViewModel::class.java)

        initializeViews()

        val adapter = com.template.presentation.ui.syndicates.SyndicatesAdapter(dataBindingComponent, appExecutors) { syndicate ->
            if (syndicate.subSyndicates?.size == 0) {
                PreferencesHelper(requireContext()).mainSyndicate = syndicate.id
                PreferencesHelper(requireContext()).subSyndicate = 0
                navController().navigate(
                        SyndicatesFragmentDirections.openHome()
                )
            }
        }
        this.adapter = adapter
        binding.rvSyndicates.adapter = adapter

        viewModel?.viewState?.observe(this, Observer {
            if (it != null) handleViewState(it)
        })
        viewModel?.errorState?.observe(this, Observer { _ ->
            showConnectionAlert(requireContext(), retryCallback = {
                binding.progressbar.visibility = View.VISIBLE
                viewModel?.getSyndicates()
            }, cancelCallback = {
                navController().navigateUp()
            })
        })

        viewModel?.getSyndicates()
    }

    private fun handleViewState(state: SyndicatesViewState) {
        binding.progressbar.visibility = if (state.isLoading) View.VISIBLE else View.GONE
        state.syndicates?.let {
            val temp = listOf(it[0])
            adapter.submitList(temp)
        }
    }

    fun initializeViews() {
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == 255 && resultCode == 200) {
            navController().navigate(
                    SyndicatesFragmentDirections.openHome()
            )
        }
    }

//region
// endregion

    fun navController() = findNavController()
}
