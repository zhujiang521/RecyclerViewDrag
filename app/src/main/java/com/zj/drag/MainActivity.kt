package com.zj.drag

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var recyclerView: RecyclerView
    private lateinit var btnSwitch: FloatingActionButton
    private lateinit var callback: NoteHelperCallBack

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        recyclerView = findViewById(R.id.recyclerView)
        btnSwitch = findViewById(R.id.btnSwitch)
        recyclerView.layoutManager = LinearLayoutManager(this)
        btnSwitch.setOnClickListener(this)
        val notes = getNotes()
        val adapter = NoteDragAdapter(notes)
        recyclerView.adapter = adapter
        callback = NoteHelperCallBack()
        ItemTouchHelper(callback).attachToRecyclerView(recyclerView)
    }

    override fun onClick(v: View?) {
        if (recyclerView.layoutManager is LinearLayoutManager) {
            val spanCount = 2
            val layoutManager =
                StaggeredGridLayoutManager(spanCount, StaggeredGridLayoutManager.VERTICAL)
            layoutManager.gapStrategy = StaggeredGridLayoutManager.GAP_HANDLING_NONE
            recyclerView.layoutManager = layoutManager

            //解决底部滚动到顶部时，顶部item上方偶尔会出现一大片间隔的问题
            recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
                override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                    val first = IntArray(spanCount)
                    layoutManager.findFirstCompletelyVisibleItemPositions(first)
                    if (newState == RecyclerView.SCROLL_STATE_IDLE && (first[0] == 1 || first[1] == 1)) {
                        layoutManager.invalidateSpanAssignments()
                    }
                }
            })
        } else {
            recyclerView.layoutManager = LinearLayoutManager(this)
        }
    }

}