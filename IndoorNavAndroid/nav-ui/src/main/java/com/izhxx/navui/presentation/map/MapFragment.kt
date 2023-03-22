package com.izhxx.navui.presentation.map

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.ui.Modifier
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import com.izhxx.navui.R
import com.izhxx.navui.databinding.NavUiFragmentMapBinding
import com.izhxx.navui.presentation.base.BaseFragment
import com.izhxx.navui.presentation.map.components.MapComposeView
import com.izhxx.navui.utils.ext.daggerComponent
import javax.inject.Inject

internal class MapFragment : BaseFragment(R.layout.nav_ui_fragment_map) {

    @Inject
    internal lateinit var viewModelFactory: ViewModelProvider.Factory
    private val mapViewModel: MapViewModel by viewModels { viewModelFactory }

    private var _binding: NavUiFragmentMapBinding? = null
    private val binding: NavUiFragmentMapBinding
        get() = _binding!!

    override fun onAttach(context: Context) {
        super.onAttach(context)
        requireContext().daggerComponent.inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = NavUiFragmentMapBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

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

    private fun initMap() {
        binding.navUiMapCompose.setContent {
            MapComposeView(modifier = Modifier, state = mapViewModel.mapState)
        }
    }

    private fun initObservers() {
        mapViewModel.isLoading.observe { }
        mapViewModel.error.observe { }
        mapViewModel.selectedLocation.observe {
            binding.navUiMapCardPointInfo.apply {
                titleText = it.locationName
                setOnClickListener { }
            }
        }
    }

}