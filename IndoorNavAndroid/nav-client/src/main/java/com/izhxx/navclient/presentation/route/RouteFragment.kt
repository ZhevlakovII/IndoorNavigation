package com.izhxx.navclient.presentation.route

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import com.izhxx.navclient.databinding.NavClientFragmentRouteBinding
import com.izhxx.navshared.base.NavFragment
import com.izhxx.navclient.utils.ext.daggerComponent
import com.izhxx.navuikit.ComposeMapView
import javax.inject.Inject

internal class RouteFragment : NavFragment() {

    @Inject
    internal lateinit var viewModelFactory: ViewModelProvider.Factory
    private val routeViewModel: RouteViewModel by viewModels<RouteViewModelImpl> { viewModelFactory }

    private var _binding: NavClientFragmentRouteBinding? = null
    private val binding
        get() = _binding!!

    override fun onAttach(context: Context) {
        super.onAttach(context)
        context.daggerComponent.inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = NavClientFragmentRouteBinding.inflate(inflater)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initMap()
        initObservers()
    }

    override fun onStart() {
        super.onStart()
        routeViewModel.onStart()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun initMap() {
        binding.navClientRouteMapCompose.setContent {
            ComposeMapView(mapState = routeViewModel.mapState)
        }
    }

    private fun initObservers() {
        routeViewModel.apply {
            isLoading.observe {  }
            error.observe {  }
        }
    }
}