package com.izhxx.indoornavarandroid.ui

import android.os.Bundle
import android.text.Editable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import com.izhxx.indoornavarandroid.adapters.HistoryViewAdapter
import com.izhxx.indoornavarandroid.adapters.SearchedLocationsViewAdapter
import com.izhxx.indoornavarandroid.databinding.SearchFragmentBinding
import com.izhxx.indoornavarandroid.viewmodels.SearchViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchFragment: Fragment()  {
    private val searchViewModel: SearchViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = SearchFragmentBinding.inflate(inflater, container, false)

        val historyViewAdapter = HistoryViewAdapter()
        val searchedLocationsViewAdapter = SearchedLocationsViewAdapter()

        binding.backButton.setOnClickListener { backButtonClickListener() }
        binding.textInputLayout.editText?.addTextChangedListener { text -> editTextChangeListener(text) }

        return binding.root
    }

    private fun editTextChangeListener(text: Editable?) {
        if (text != null) {
            if (text.isNotEmpty()) {
                searchViewModel.searchLocation(text.toString())
            }
        }
    }

    private fun backButtonClickListener() {
        view?.findNavController()?.navigateUp()
    }

    private fun subscribeUi(binding: SearchFragmentBinding) {
        searchViewModel.historyList.observe(viewLifecycleOwner) { historyList ->
            //TODO()
        }

        searchViewModel.searchedLocation.observe(viewLifecycleOwner) { locations ->

        }
    }
}