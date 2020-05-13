package com.template.presentation.ui.home

import androidx.lifecycle.Observer
import androidx.databinding.DataBindingComponent
import androidx.databinding.DataBindingUtil
import android.os.Bundle
import androidx.drawerlayout.widget.DrawerLayout
import androidx.appcompat.app.AppCompatActivity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.template.AppExecutors
import com.template.R
import com.template.databinding.HomeFragmentBinding
import com.template.presentation.binding.FragmentDataBindingComponent
import com.template.presentation.common.BaseFragment
import com.template.presentation.util.autoCleared
import com.template.testing.OpenForTesting
import kotlinx.android.synthetic.main.main_activity.*
import javax.inject.Inject

@OpenForTesting
class HomeFragment : BaseFragment<HomeViewModel>() {
//    @Inject
//    lateinit var viewModelFactory: ViewModelProvider.Factory

    var dataBindingComponent: DataBindingComponent = FragmentDataBindingComponent(this)

    var binding by autoCleared<HomeFragmentBinding>()

//    lateinit var homeViewModel: HomeViewModel

    @Inject
    lateinit var appExecutors: AppExecutors

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
                inflater,
                R.layout.home_fragment,
                container,
                false,
                dataBindingComponent
        )
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (requireActivity() as AppCompatActivity).supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            setHomeAsUpIndicator(R.mipmap.menu_ic)
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
//        homeViewModel = ViewModelProviders.of(this, viewModelFactory)
//                .get(HomeViewModel::class.java)

        viewModel?.viewState?.observe(this, Observer {
            if (it != null) handleViewState(it)
        })
        viewModel?.errorState?.observe(this, Observer { _ ->
            showConnectionAlert(requireContext(), retryCallback = {
                binding.progressbar.visibility = View.VISIBLE
            }, cancelCallback = {
                navController().navigateUp()
            })
        })
    }

    override fun onResume() {
        super.onResume()
        initializeViews()
    }

    private fun handleViewState(state: HomeViewState) {
        binding.progressbar.visibility = if (state.isLoading) View.VISIBLE else View.GONE
    }

    fun initializeViews() {
        (activity as AppCompatActivity).drawer_layout.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED)
    }

//region

// endregion

    fun navController() = findNavController()
}
