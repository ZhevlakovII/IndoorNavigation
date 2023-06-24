package com.izhxx.navclient.presentation.search.adapters.search

import androidx.recyclerview.widget.DiffUtil
import com.izhxx.navclient.presentation.search.model.SearchedItemPresentation

internal class SearchDiffUtil : DiffUtil.ItemCallback<SearchedItemPresentation>() {

    override fun areItemsTheSame(
        oldItem: SearchedItemPresentation,
        newItem: SearchedItemPresentation,
    ): Boolean {
        return oldItem.locationId == newItem.locationId
    }

    override fun areContentsTheSame(
        oldItem: SearchedItemPresentation,
        newItem: SearchedItemPresentation,
    ): Boolean {
        return oldItem == newItem
    }

}