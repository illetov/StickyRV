package com.example.stickyrv

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.layout_header.view.*
import kotlinx.android.synthetic.main.layout_item.view.*


const val VIEW_TYPE_HEADER: Int = 1
const val VIEW_TYPE_ITEM: Int = 2

class MainAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>(), StickDecor.StickyHeaderInterface {
    private var localItems: List<Composite> = emptyList()
    fun setDataSet(data: List<Composite>) {
        localItems = data
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)

        return when (viewType) {
            VIEW_TYPE_HEADER -> HeaderViewHolder(
                inflater.inflate(
                    R.layout.layout_header,
                    parent,
                    false
                )
            )

            VIEW_TYPE_ITEM -> ItemViewHolder(
                inflater.inflate(
                    R.layout.layout_item,
                    parent,
                    false)
            )

            else -> throw IllegalStateException("Incorrect view type")
        }

    }

    override fun getItemCount(): Int = localItems.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (localItems[position].getViewType() == 1) {
            (holder as HeaderViewHolder).bind(localItems[position] as HeaderView)
        } else {
            (holder as ItemViewHolder).bind(localItems[position] as ItemView)
        }

    }

    override fun getItemViewType(position: Int): Int {
        return localItems[position].getViewType()
    }

    override fun isHeader(itemPosition: Int): Boolean {
        return localItems[itemPosition].getViewType() == 1
    }

    override fun getHeaderLayout(headerPosition: Int): Int {
       return if(localItems[headerPosition].getViewType() == 1)  R.layout.layout_header else R.layout.layout_item
    }

    override fun getHeaderPositionForItem(itemPosition: Int): Int {
        var headerPosition = 0
        var tempLayout = itemPosition
        do {
            if (isHeader(tempLayout)) {
                headerPosition = tempLayout
                break
            }
            tempLayout -= 1
        } while (tempLayout >= 0)
        return headerPosition
    }

    override fun bindHeaderData(header: View?, headerPosition: Int) {
        header?.header_view?.text = (localItems[headerPosition] as HeaderView).title
    }
}



class HeaderViewHolder(item: View) : RecyclerView.ViewHolder(item) {
    fun bind(model: HeaderView){
        itemView.header_view.text = model.title
    }
}

class ItemViewHolder(item: View) : RecyclerView.ViewHolder(item) {
    fun bind(model: ItemView){
        itemView.item_view.text = model.title
    }
}

