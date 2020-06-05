package com.example.stickyrv

const val ITEM_VIEW_TYPE = 2

data class ItemView(
    var title: String
): Composite {
    override fun getViewType(): Int = ITEM_VIEW_TYPE
}