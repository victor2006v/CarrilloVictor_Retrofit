package com.example.carrillovictor_retrofit.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.carrillovictor_retrofit.databinding.ItemPostBinding
import com.example.carrillovictor_retrofit.model.Post

class PostAdapter(private var posts: List<Post>) : RecyclerView.Adapter<PostAdapter.PostViewHolder>() {

    class PostViewHolder(val binding: ItemPostBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        val binding = ItemPostBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PostViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        val post = posts[position]
        holder.binding.tvPostId.text = "Post ID: ${post.id}"
        holder.binding.tvPostTitle.text = post.title
        holder.binding.tvPostBody.text = post.body
    }

    override fun getItemCount(): Int = posts.size

    fun updateData(newPosts: List<Post>) {
        posts = newPosts
        notifyDataSetChanged()
    }
}
