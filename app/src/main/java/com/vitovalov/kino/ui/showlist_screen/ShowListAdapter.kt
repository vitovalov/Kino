package com.vitovalov.kino.ui.showlist_screen

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.vitovalov.kino.R
import com.vitovalov.kino.domain.model.Show
import com.vitovalov.kino.extensions.inflate
import com.vitovalov.kino.extensions.load
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.adapter_show_list.view.*
import kotlin.random.Random

class ShowListAdapter : RecyclerView.Adapter<ShowListAdapter.ShowListViewHolder>() {

    private var items: MutableList<Show> = mutableListOf()

    //region adapter
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShowListViewHolder =
        ShowListViewHolder(parent.inflate(R.layout.adapter_show_list))

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: ShowListViewHolder, position: Int) =
        holder.bind(items[position], position)

    //endregion

    fun addItems(items: List<Show>) {
        val previousSize = this.items.size
        this.items.addAll(items)
        if (this.items.isEmpty()) notifyDataSetChanged() else
            notifyItemRangeInserted(previousSize, this.items.size)
    }

    inner class ShowListViewHolder constructor(
        override val containerView: View
    ) : RecyclerView.ViewHolder(containerView), LayoutContainer {

        fun bind(item: Show, position: Int) =
            with(itemView) {
                adapter_show_list_iv.layoutParams.height = Random.nextInt(150, 450)
                adapter_show_list_iv.load(item.backdropPath)
                adapter_show_list_title_tv.text = item.name + "  ${item.voteAverage}★"
            }
    }
}
