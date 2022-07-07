package com.izhxx.indoornavarandroid.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import com.izhxx.indoornavarandroid.databinding.MapFragmentBinding
import com.izhxx.indoornavarandroid.viewmodels.MapViewModel
import com.izhxx.indoornavarandroid.viewmodels.SharedViewModel
import dagger.hilt.android.AndroidEntryPoint
import ovh.plrapps.mapcompose.ui.MapUI

@AndroidEntryPoint
class MapFragment: Fragment() {
    private val mapViewModel: MapViewModel by viewModels()
    private val sharedViewModel: SharedViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = MapFragmentBinding.inflate(inflater, container, false)

        binding.mapComposeView.setContent { MapContainer(viewModel = mapViewModel) }
        binding.searchButton.setOnClickListener { searchButtonClickListener() }
        binding.pickPointButton.setOnClickListener { pickPointButtonClickListener() }

        subscribeUi(binding)

        return binding.root
    }

    private fun pickPointButtonClickListener() {
        val clickDestination = MapFragmentDirections.actionMapToPointSelection()
        view?.findNavController()?.navigate(clickDestination)

        mapViewModel.changeTapState(true)
    }

    private fun searchButtonClickListener() {
        val clickDestination = MapFragmentDirections.actionMapToSearch()
        view?.findNavController()?.navigate(clickDestination)
    }

    private fun subscribeUi(binding: MapFragmentBinding) {
        mapViewModel.locations.observe(viewLifecycleOwner) { locationList ->
            if (locationList.isEmpty())
                mapViewModel.changeTapActivate(false)
            else
                mapViewModel.changeTapActivate(true)
        }

        mapViewModel.isTapOnMap.observe(viewLifecycleOwner) { tapState ->
            if (tapState) {
                binding.navigationCard.visibility = View.VISIBLE
                binding.searchedItem = mapViewModel.getLocationName()
                sharedViewModel.changeCardState(tapState)
            }
            else {
                binding.navigationCard.visibility = View.GONE
                sharedViewModel.changeCardState(tapState)
            }
        }
    }
}

@Composable
private fun MapContainer(
    modifier: Modifier = Modifier,
    viewModel: MapViewModel
) {
    MapUI(modifier, state = viewModel.mapState)
}