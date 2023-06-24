package com.izhxx.navclient.presentation.map

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.izhxx.navclient.R
import com.izhxx.navclient.databinding.NavClientFragmentMapBinding
import com.izhxx.navshared.base.NavFragment
import com.izhxx.navclient.domain.model.error.ErrorModel
import com.izhxx.navclient.domain.model.error.ErrorType
import com.izhxx.navclient.utils.ext.daggerComponent
import com.izhxx.navuikit.ComposeMapView
import com.izhxx.navuikit.NavUiKitToast
import com.izhxx.navuikit.ext.visible
import javax.inject.Inject

internal class MapFragment : NavFragment() {

    @Inject
    internal lateinit var viewModelFactory: ViewModelProvider.Factory
    private val mapViewModel: MapViewModel by viewModels<MapViewModelImpl> { viewModelFactory }

    private var _binding: NavClientFragmentMapBinding? = null
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
        _binding = NavClientFragmentMapBinding.inflate(inflater)

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
        mapViewModel.onStart()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun initButtons() {
        binding.apply {
            navClientMapButtonSearch.setOnClickListener {
                view.findNavController().navigate(R.id.navClientMapFragmentToSearchFragment)
            }
            navClientMapCardPointInfo.setOnClickListener {
                mapViewModel.onButtonNavigateClick()
            }
        }
    }

    private fun initMap() {
        binding.navClientMapCompose.setContent {
            ComposeMapView(mapState = mapViewModel.mapState)
        }
    }

    private fun initObservers() {
        mapViewModel.apply {
            isLoading.observe { blockUI(it) }
            error.observe { error -> showError(error) }
            selectedLocation.observe {
                binding.navClientMapCardPointInfo.apply {
                    titleText = it.locationName
                    visible()
                }
            }
            openNavigationScreen.observe { navigate ->
                if (navigate) view.findNavController()
                    .navigate(R.id.navClientMapFragmentToNavigationFragment)
            }
        }
    }

    private fun blockUI(isBlock: Boolean) {
        binding.apply {
            navClientMapButtonSearch.isEnabled = !isBlock
            navClientMapCardPointInfo.isEnabled = !isBlock
        }
    }

    private fun showError(error: ErrorModel) {
        when (error.errorType) {
            ErrorType.TOAST_SHOWS -> {
                val message = error.errorMessage
                    ?: error.errorMessageResource?.let { messageId -> getString(messageId) }
                    ?: getString(R.string.nav_client_ui_error_unknown)

                NavUiKitToast.Builder(requireView())
                    .error()
                    .duration(duration = NavUiKitToast.DURATION_MEDIUM)
                    .message(message = message)
                    .show()
            }
            else -> {}
        }
    }

}