package com.vitovalov.kino.ui.showlist

import android.graphics.Color
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.vitovalov.kino.R
import com.vitovalov.kino.extensions.inflate
import com.vitovalov.kino.ui.model.ShowUo
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.adapter_show_list.view.*

class ShowListAdapter : RecyclerView.Adapter<ShowListAdapter.ShowListViewHolder>() {

    private var items: MutableList<ShowUo> = mutableListOf()

    //region adapter
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShowListViewHolder =
        ShowListViewHolder(parent.inflate(R.layout.adapter_show_list))

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: ShowListViewHolder, position: Int) =
        holder.bind(items[position], position)

    //endregion

    fun addItems(items: List<ShowUo>) {
        this.items.addAll(items)
        notifyDataSetChanged()
    }

    inner class ShowListViewHolder constructor(
        override val containerView: View
    ) : RecyclerView.ViewHolder(containerView), LayoutContainer {

        fun bind(item: ShowUo, position: Int) =
            with(itemView) {
                adapter_show_list_title_tv.text = item.name
                if (position % 2 == 0) {
                    adapter_show_list_title_tv.setBackgroundColor(Color.WHITE)
                }
            }
    }
}