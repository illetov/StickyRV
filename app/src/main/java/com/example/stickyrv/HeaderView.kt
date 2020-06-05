package com.example.stickyrv

data class HeaderView(
    var title: String,
    val row: String
): Composite{
    override fun getViewType(): Int {
        return 1
    }
}