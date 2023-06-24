package com.izhxx.navclient.presentation.search.adapters.search

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.izhxx.navclient.R
import com.izhxx.navclient.presentation.search.model.SearchedItemPresentation

internal class SearchVH(view: View) : RecyclerView.ViewHolder(view) {

    private val textView: TextView = view.findViewById(R.id.navClientSearchRecyclerItemTextView)

    companion object {
        internal fun create(parent: ViewGroup): SearchVH = SearchVH(
            LayoutInflater.from(parent.context).inflate(
                R.layout.nav_client_recycler_view_search_item,
                parent,
                false
            )
        )
    }

    fun bind(
        item: SearchedItemPresentation,
        onItemClick: ((Int) -> Unit)
    ) {
        textView.text = item.locationName
        itemView.setOnClickListener { onItemClick(item.locationId) }
    }

}