package com.izhxx.indoornavarandroid.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import com.google.accompanist.appcompattheme.AppCompatTheme
import com.izhxx.indoornavarandroid.databinding.MapFragmentBinding
import com.izhxx.indoornavarandroid.viewmodels.MapViewModel
import ovh.plrapps.mapcompose.ui.MapUI

class MapFragment: Fragment() {
    private val mapViewModel: MapViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) : View {
        val binding = MapFragmentBinding.inflate(inflater, container, false)

        binding.mapComposeView.setContent {
            AppCompatTheme {
                MapContainer(viewModel = mapViewModel)
            }
        }
        binding.searchButton.setOnClickListener { searchButtonClickListener() }
        binding.pickPointButton.setOnClickListener { pickPointButtonClickListener() }

        return binding.root
    }

    private fun pickPointButtonClickListener() {
        val clickDestination = MapFragmentDirections.actionMapToPointSelection()
        view?.findNavController()?.navigate(clickDestination)
    }

    private fun searchButtonClickListener() {
        val clickDestination = MapFragmentDirections.actionMapToSearch()
        view?.findNavController()?.navigate(clickDestination)
    }
}

@Composable
private fun MapContainer(
    modifier: Modifier = Modifier,
    viewModel: MapViewModel
) {
    MapUI(modifier, state = viewModel.mapState)
}