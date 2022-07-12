package com.izhxx.indoornavarandroid.ui

import android.os.Bundle
import android.text.Editable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs
import com.izhxx.indoornavarandroid.adapters.HistoryViewAdapter
import com.izhxx.indoornavarandroid.adapters.SearchedLocationsViewAdapter
import com.izhxx.indoornavarandroid.databinding.SearchFragmentBinding
import com.izhxx.indoornavarandroid.viewmodels.SearchViewModel
import com.izhxx.indoornavarandroid.viewmodels.SharedViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchFragment: Fragment()  {
    private val searchViewModel: SearchViewModel by viewModels()
    private val sharedViewModel: SharedViewModel by activityViewModels()

    private val searchFragmentArguments: SearchFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = SearchFragmentBinding.inflate(inflater, container, false)

        val historyViewAdapter = HistoryViewAdapter(sharedViewModel, searchFragmentArguments.isOpenFromMap)
        val searchedLocationsViewAdapter = SearchedLocationsViewAdapter(sharedViewModel, searchFragmentArguments.isOpenFromMap)

        binding.recyclerviewHistory.adapter = historyViewAdapter
        binding.recyclerviewSearchedItems.adapter = searchedLocationsViewAdapter

        binding.backButton.setOnClickListener { backButtonClickListener() }
        binding.textInputLayout.editText?.addTextChangedListener {
            editTextChangeListener(it, binding)
            subscribeSearchedList(searchedLocationsViewAdapter)
        }

        subscribeHistoryList(historyViewAdapter)

        return binding.root
    }

    private fun editTextChangeListener(text: Editable?, binding: SearchFragmentBinding) {
        text.let {
            if (it.isNullOrEmpty()) {
                binding.recyclerviewHistory.visibility = View.VISIBLE
                binding.recyclerviewSearchedItems.visibility = View.GONE
            } else {
                searchViewModel.searchLocation(it.toString())

                binding.recyclerviewHistory.visibility = View.GONE
                binding.recyclerviewSearchedItems.visibility = View.VISIBLE
            }
        }
    }

    private fun backButtonClickListener() {
        view?.findNavController()?.navigateUp()
    }

    private fun subscribeHistoryList(
        historyAdapter: HistoryViewAdapter
    ) {
        searchViewModel.historyList.observe(viewLifecycleOwner) { historyList ->
            if (historyList != null) historyAdapter.submitList(historyList)
        }
    }

    private fun subscribeSearchedList(
        searchedLocationsAdapter: SearchedLocationsViewAdapter
    ) {
        searchViewModel.searchedLocation.observe(viewLifecycleOwner) { locations ->
            if (locations != null) searchedLocationsAdapter.submitList(locations)
        }
    }
}