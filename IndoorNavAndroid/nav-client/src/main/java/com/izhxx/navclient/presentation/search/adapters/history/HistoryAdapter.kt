package com.izhxx.navclient.presentation.search.adapters.history

import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.izhxx.navclient.presentation.search.model.SearchHistoryPresentation

internal class HistoryAdapter(
    private val onItemClick: ((Int) -> Unit)
) : ListAdapter<SearchHistoryPresentation, HistoryVH>(HistoryDiffUtil()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HistoryVH =
        HistoryVH.create(parent)

    override fun onBindViewHolder(holder: HistoryVH, position: Int) =
        holder.bind(getItem(position), onItemClick)

}