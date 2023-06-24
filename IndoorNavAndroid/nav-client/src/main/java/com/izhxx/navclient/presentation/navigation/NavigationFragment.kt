package com.izhxx.navclient.presentation.navigation

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.izhxx.navclient.R
import com.izhxx.navclient.databinding.NavClientFragmentNavigationBinding
import com.izhxx.navshared.base.NavFragment
import com.izhxx.navclient.utils.ext.daggerComponent
import com.izhxx.navuikit.ComposeMapView
import javax.inject.Inject

internal class NavigationFragment : NavFragment() {

    @Inject
    internal lateinit var viewModelFactory: ViewModelProvider.Factory
    private val navigationViewModel: NavigationViewModel by viewModels<NavigationViewModelImpl> { viewModelFactory }

    private var _binding: NavClientFragmentNavigationBinding? = null
    private val binding
        get() = _binding!!

    override fun onAttach(context: Context) {
        super.onAttach(context)
        context.daggerComponent.inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = NavClientFragmentNavigationBinding.inflate(inflater)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initButtons()
        initMap()
        initObservers()
    }

    override fun onStart() {
        super.onStart()
        navigationViewModel.onStart()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun initButtons() {
        binding.navClientNavigationPointsInput.apply {
            setBackButtonClickListener {
                view.findNavController().navigate(R.id.navClientNavigationFragmentToMapFragment)
            }
            setCurrentPositionButtonClickListener {
                view.findNavController().navigate(R.id.navClientNavigationFragmentToSearchFragment)
                navigationViewModel.onCurrentPositionButtonClick()
            }
            setDestinationButtonClickListener {
                view.findNavController().navigate(R.id.navClientNavigationFragmentToSearchFragment)
                navigationViewModel.onDestinationButtonClick()
            }
        }
        binding.navClientNavigationCardPointInfo.setOnClickListener {
            navigationViewModel.onNavigateButtonClick()
        }
    }

    private fun initMap() {
        binding.navClientNavigationCompose.setContent {
            ComposeMapView(mapState = navigationViewModel.mapState)
        }
    }

    private fun initObservers() {
        navigationViewModel.apply {
            isLoading.observe { }
            error.observe { }

            currentPosition.observe {
                binding.navClientNavigationPointsInput.currentPositionButtonText = it.locationName
            }
            destination.observe {
                binding.navClientNavigationPointsInput.destinationButtonText = it.locationName
            }

            openRouteScreen.observe { navigate ->
                if (navigate) view.findNavController()
                    .navigate(R.id.navClientNavigationFragmentToRouteFragment)
            }
        }
    }

}