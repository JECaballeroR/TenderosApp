package com.slabcode.agutierrezt.example2.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.slabcode.agutierrezt.example2.data.models.Comment
import com.slabcode.agutierrezt.example2.databinding.ItemCommentBinding
import com.slabcode.agutierrezt.example2.ui.listeners.OnCommentListener

class CommentAdapter(var items: List<Comment>): RecyclerView.Adapter<CommentAdapter.ViewHolder>() {

    var listener: OnCommentListener? = null

    class ViewHolder(val view: ItemCommentBinding): RecyclerView.ViewHolder(view.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemCommentBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val comment = items[position]
        holder.view.itemCommentName.text = comment.name
        holder.view.itemCommentMessage.text = comment.message
        Glide.with(holder.itemView).load(comment.image).into(holder.view.itemCommentPhoto)

        holder.view.root.setOnClickListener {
            listener?.onClick(comment)
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }

    fun newDataset(comments: List<Comment>) {
        items = comments
        notifyDataSetChanged()
    }
}