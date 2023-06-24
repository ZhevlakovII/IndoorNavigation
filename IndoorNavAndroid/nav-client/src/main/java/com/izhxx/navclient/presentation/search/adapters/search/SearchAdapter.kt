package com.izhxx.navclient.presentation.search.adapters.search

import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.izhxx.navclient.presentation.search.model.SearchedItemPresentation

internal class SearchAdapter(
    private val onItemClick: ((Int) -> Unit)
) : ListAdapter<SearchedItemPresentation, SearchVH>(SearchDiffUtil()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchVH =
        SearchVH.create(parent)

    override fun onBindViewHolder(holder: SearchVH, position: Int) =
        holder.bind(getItem(position), onItemClick)

}