package com.izhxx.navclient.presentation.search

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.izhxx.navclient.R
import com.izhxx.navclient.databinding.NavClientFragmentSearchBinding
import com.izhxx.navclient.presentation.search.adapters.history.HistoryAdapter
import com.izhxx.navclient.presentation.search.adapters.search.SearchAdapter
import com.izhxx.navshared.base.NavFragment
import com.izhxx.navclient.utils.ext.daggerComponent
import javax.inject.Inject

internal class SearchFragment : NavFragment() {

    @Inject
    internal lateinit var viewModelFactory: ViewModelProvider.Factory
    private val searchViewModel: SearchViewModel by viewModels<SearchViewModelImpl> { viewModelFactory }

    private val historyAdapter = HistoryAdapter(
        onItemClick = { searchViewModel.selectItem(it) }
    )
    private val searchAdapter = SearchAdapter(
        onItemClick = { searchViewModel.selectItem(it) }
    )

    private var _binding: NavClientFragmentSearchBinding? = null
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
        _binding = NavClientFragmentSearchBinding.inflate(inflater)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initEditText()
        initRecyclers()
        initObservers()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun initEditText() {
        binding.navClientSearchEditText.apply {
            onCompleteTextChange = { searchViewModel.findLocation(it) }
            setOnClickListener {
                view.findNavController().popBackStack()
            }
        }
    }

    private fun initRecyclers() {
        binding.apply {
            navClientSearchRecyclerViewSearchHistory.adapter = historyAdapter
            navClientSearchRecyclerViewSearchedItems.adapter = searchAdapter
        }
    }

    private fun initObservers() {
        searchViewModel.apply {
            isLoading.observe { }
            error.observe { }
            historyLocations.observe {
                historyAdapter.submitList(it)
            }
            searchedLocations.observe {}
            openNavigationScreen.observe { navigate ->
                if (navigate) view.findNavController()
                    .navigate(R.id.navClientSearchFragmentToNavigationFragment)
            }
        }
    }

}