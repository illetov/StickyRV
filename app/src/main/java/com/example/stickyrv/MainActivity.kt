package com.example.stickyrv

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerView = main_recycler

        val adapter = MainAdapter()
        adapter.setDataSet(generateDataSet())

        recyclerView.adapter = adapter
        recyclerView.addItemDecoration(StickDecor(adapter))
    }

    private fun generateDataSet(): List<Composite>{
        val list = mutableListOf<Composite>()

        for (item in 0..300){
            if(item % 24 == 0){
                list.add(HeaderView("header $item", "empty"))
            } else{
                val content = ItemView("title number $item")
                list.add(content)
            }

        }
        return list
    }
}