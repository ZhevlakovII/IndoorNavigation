package com.izhxx.indoornavarandroid.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.izhxx.indoornavarandroid.data.databases.searchhistorydatabase.SearchHistory
import com.izhxx.indoornavarandroid.databinding.RecyclerviewItemHistoryBinding

class HistoryViewAdapter: ListAdapter<SearchHistory, RecyclerView.ViewHolder>(SearchHistoryDiffCallback()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return HistoryViewHolder(
            RecyclerviewItemHistoryBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        //TODO()
    }

    class HistoryViewHolder(
        binding: RecyclerviewItemHistoryBinding
    ): RecyclerView.ViewHolder(binding.root) {
        //TODO()
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
