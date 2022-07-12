package com.izhxx.indoornavarandroid.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.izhxx.indoornavarandroid.R
import com.izhxx.indoornavarandroid.data.databases.searchhistorydatabase.SearchHistory
import com.izhxx.indoornavarandroid.data.databases.searchhistorydatabase.SearchHistoryRepo
import com.izhxx.indoornavarandroid.databinding.RecyclerviewItemHistoryBinding
import com.izhxx.indoornavarandroid.ui.SearchFragmentDirections
import com.izhxx.indoornavarandroid.viewmodels.SharedViewModel
import javax.inject.Inject

class HistoryViewAdapter (
    private val sharedViewModel: SharedViewModel,
    private val isOpenFromMap: Boolean
): ListAdapter<SearchHistory, RecyclerView.ViewHolder>(SearchHistoryDiffCallback()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            RecyclerviewItemHistoryBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            ),
            sharedViewModel,
            isOpenFromMap
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val history = getItem(position)
        (holder as ViewHolder).bind(history)
    }

    class ViewHolder(
        private val binding: RecyclerviewItemHistoryBinding,
        private val sharedViewModel: SharedViewModel,
        private val isOpenFromMap: Boolean
    ): RecyclerView.ViewHolder(binding.root) {
        init {
            binding.setClickListener {
                binding.history.let { item ->
                    sharedViewModel.changePickedLocationWithId(item.searchedLocationId)
                    sharedViewModel.insertHistoryItem(item)
                    navigateToPointsSelection(it, isOpenFromMap)
                }
            }

            binding.iconRvHistory.setImageResource(R.drawable.ic_history_24dp)
        }

        fun bind(item: SearchHistory) {
            binding.apply {
                history = item
                executePendingBindings()
            }
        }

        private fun navigateToPointsSelection(view: View, isOpenFromMap: Boolean) {
            if (isOpenFromMap) {
                val direction = SearchFragmentDirections.actionSearchToPointSelection()
                view.findNavController().navigate(direction)
            } else {
                view.findNavController().navigateUp()
            }
        }
    }
}

private class SearchHistoryDiffCallback: DiffUtil.ItemCallback<SearchHistory>() {
    override fun areItemsTheSame(oldItem: SearchHistory, newItem: SearchHistory): Boolean {
        return oldItem.searchId == newItem.searchId
    }

    override fun areContentsTheSame(oldItem: SearchHistory, newItem: SearchHistory): Boolean {
        return oldItem == newItem
    }
}
