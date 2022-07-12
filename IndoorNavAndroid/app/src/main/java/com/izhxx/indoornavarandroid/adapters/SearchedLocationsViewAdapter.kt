package com.izhxx.indoornavarandroid.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.izhxx.indoornavarandroid.R
import com.izhxx.indoornavarandroid.data.databases.locationsdatabase.Location
import com.izhxx.indoornavarandroid.databinding.RecyclerviewItemSearchedBinding
import com.izhxx.indoornavarandroid.ui.SearchFragmentDirections
import com.izhxx.indoornavarandroid.viewmodels.SharedViewModel

class SearchedLocationsViewAdapter(
    private val sharedViewModel: SharedViewModel,
    private val isOpenFromMap: Boolean
): ListAdapter<Location, RecyclerView.ViewHolder>(SearchedItemsDiffCallback())  {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return ViewHolder(
            RecyclerviewItemSearchedBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            ),
            sharedViewModel,
            isOpenFromMap
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = getItem(position)
        (holder as ViewHolder).bind(item)
    }

    class ViewHolder(
        private val binding: RecyclerviewItemSearchedBinding,
        private val sharedViewModel: SharedViewModel,
        private val isOpenFromMap: Boolean
    ): RecyclerView.ViewHolder(binding.root) {
        init {
            binding.setClickListener {
                binding.searchedItem.let { item ->
                    sharedViewModel.changePickedLocationWithId(item.locationId)
                    sharedViewModel.insertNewItem(item)
                    navigateToPointsSelection(it, isOpenFromMap)
                }
            }

            binding.iconRvSearched.setImageResource(R.drawable.ic_map_pin_24dp)
        }

        fun bind(item: Location) {
            binding.apply {
                searchedItem = item
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

private class SearchedItemsDiffCallback: DiffUtil.ItemCallback<Location>() {
    override fun areItemsTheSame(oldItem: Location, newItem: Location): Boolean {
        return oldItem.locationId == newItem.locationId
    }

    override fun areContentsTheSame(oldItem: Location, newItem: Location): Boolean {
        return oldItem == newItem
    }
}
