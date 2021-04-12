package com.zj.drag

import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import java.util.*

class NoteHelperCallBack : ItemTouchHelper.Callback() {

    /**
     * 设置拖拽、滑动方向
     */
    override fun getMovementFlags(
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder
    ): Int {
        //拖拽方向
        val dragFlags =
            ItemTouchHelper.UP or ItemTouchHelper.DOWN or ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT
        return makeMovementFlags(dragFlags, 0)
    }

    /**
     * 拖拽移动
     */
    override fun onMove(
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder,
        target: RecyclerView.ViewHolder
    ): Boolean {
        //不同类型的item不能移动
        if (viewHolder.itemViewType != target.itemViewType) {
            return false
        }

        //拖动的position
        val fromPosition = viewHolder.adapterPosition
        //释放的position
        val targetPosition = target.adapterPosition
        val adapter = recyclerView.adapter!! as NoteDragAdapter
        itemMove(adapter, adapter.mData, fromPosition, targetPosition)
        return true
    }

    /**
     * 侧滑
     */
    override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
        // 侧滑暂时不做处理
    }

    /**
     * 长按时调用
     */
    override fun onSelectedChanged(viewHolder: RecyclerView.ViewHolder?, actionState: Int) {
        super.onSelectedChanged(viewHolder, actionState)
        viewHolder?.itemView?.apply {
            //选中的改变样式
            alpha = 1f
            scaleX = 1.1f
            scaleY = 1.1f
        }

    }

    /**
     * 松手时会最后调用
     */
    override fun clearView(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder) {
        super.clearView(recyclerView, viewHolder)
        //完成移动，选中的改变样式
        viewHolder.itemView.apply {
            alpha = 1f
            scaleX = 1f
            scaleY = 1f
        }

    }

    /**
     * 是否支持长按，默认true
     */
    override fun isLongPressDragEnabled(): Boolean {
        return super.isLongPressDragEnabled()
    }

    /**
     * 是否支持侧滑，默认true
     */
    override fun isItemViewSwipeEnabled(): Boolean {
        return false
    }


    /**
     * 移动item
     *
     * @param fromPosition   长按的item,position
     * @param targetPosition 要到达的position
     */
    private fun itemMove(
        adapter: RecyclerView.Adapter<RecyclerView.ViewHolder>,
        data: List<*>,
        fromPosition: Int,
        targetPosition: Int
    ) {
        if (data.isEmpty()) {
            return
        }

        if (fromPosition < targetPosition) {
            for (i in fromPosition until targetPosition) {
                Collections.swap(data, i, i + 1)
            }
        } else {
            for (i in targetPosition until fromPosition) {
                Collections.swap(data, i, i + 1)
            }
        }
        adapter.notifyItemMoved(fromPosition, targetPosition)
    }

}