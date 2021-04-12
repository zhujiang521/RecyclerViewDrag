package com.zj.drag

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView

class NoteDragAdapter(val mData: ArrayList<Note>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return DragViewHolder(parent)
    }

    override fun getItemCount(): Int {
        return mData.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is DragViewHolder) {
            holder.onBind(mData[position])
        }
    }

    class DragViewHolder(parent: ViewGroup) : RecyclerView.ViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.adapter_note, parent, false)
    ) {
        var adTvTitle: TextView = itemView.findViewById(R.id.adTvTitle)
        private var adTvContent: TextView = itemView.findViewById(R.id.adTvContent)
        private var adIvImage: ImageView = itemView.findViewById(R.id.adIvImage)
        fun onBind(note: Note) {
            adTvTitle.text = note.title
            adTvContent.text = note.content
            adIvImage.isVisible = note.img != null
            adIvImage.setImageResource(note.img ?: 0)
            itemView.setOnClickListener {
                Toast.makeText(it.context,note.title,Toast.LENGTH_SHORT).show()
            }
        }
    }

}