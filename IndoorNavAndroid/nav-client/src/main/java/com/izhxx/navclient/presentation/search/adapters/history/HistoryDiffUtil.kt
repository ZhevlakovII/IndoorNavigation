package com.izhxx.navclient.presentation.search.adapters.history

import androidx.recyclerview.widget.DiffUtil
import com.izhxx.navclient.presentation.search.model.SearchHistoryPresentation

internal class HistoryDiffUtil : DiffUtil.ItemCallback<SearchHistoryPresentation>() {

    override fun areItemsTheSame(
        oldItem: SearchHistoryPresentation,
        newItem: SearchHistoryPresentation,
    ): Boolean {
        return oldItem.requestId == newItem.requestId
    }

    override fun areContentsTheSame(
        oldItem: SearchHistoryPresentation,
        newItem: SearchHistoryPresentation,
    ): Boolean {
        return oldItem == newItem
    }

}