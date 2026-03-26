package com.example.carrillovictor_retrofit.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.carrillovictor_retrofit.databinding.ItemCommentBinding
import com.example.carrillovictor_retrofit.model.Comment

class CommentAdapter(private var comments: List<Comment>) : RecyclerView.Adapter<CommentAdapter.CommentViewHolder>() {

    class CommentViewHolder(val binding: ItemCommentBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommentViewHolder {
        val binding = ItemCommentBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CommentViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CommentViewHolder, position: Int) {
        val comment = comments[position]
        holder.binding.tvCommentName.text = comment.name
        holder.binding.tvCommentEmail.text = comment.email
        holder.binding.tvCommentBody.text = comment.body
    }

    override fun getItemCount(): Int = comments.size

    fun updateData(newComments: List<Comment>) {
        comments = newComments
        notifyDataSetChanged()
    }
}
