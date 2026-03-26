package com.example.carrillovictor_retrofit

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.carrillovictor_retrofit.adapter.CommentAdapter
import com.example.carrillovictor_retrofit.databinding.ActivityPostDetailsBinding
import com.example.carrillovictor_retrofit.model.Comment
import com.example.carrillovictor_retrofit.model.Post
import com.example.carrillovictor_retrofit.network.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PostDetailsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityPostDetailsBinding
    private lateinit var commentAdapter: CommentAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPostDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupRecyclerView()

        binding.btnSearch.setOnClickListener {
            val postIdText = binding.etPostId.text.toString()
            if (postIdText.isNotEmpty()) {
                val postId = postIdText.toInt()
                fetchPostAndComments(postId)
            } else {
                Toast.makeText(this, "Please enter a Post ID", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun setupRecyclerView() {
        commentAdapter = CommentAdapter(emptyList())
        binding.rvComments.layoutManager = LinearLayoutManager(this)
        binding.rvComments.adapter = commentAdapter
    }

    private fun fetchPostAndComments(postId: Int) {
        // Fetch Post
        RetrofitClient.apiService.getPostById(postId).enqueue(object : Callback<Post> {
            override fun onResponse(call: Call<Post>, response: Response<Post>) {
                if (response.isSuccessful) {
                    response.body()?.let {
                        binding.tvPostTitle.text = it.title
                        binding.tvPostBody.text = it.body
                    }
                } else {
                    Toast.makeText(this@PostDetailsActivity, "Post not found", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<Post>, t: Throwable) {
                Toast.makeText(this@PostDetailsActivity, "Failure fetching post: ${t.message}", Toast.LENGTH_SHORT).show()
            }
        })

        // Fetch Comments
        RetrofitClient.apiService.getCommentsByPostId(postId).enqueue(object : Callback<List<Comment>> {
            override fun onResponse(call: Call<List<Comment>>, response: Response<List<Comment>>) {
                if (response.isSuccessful) {
                    response.body()?.let {
                        commentAdapter.updateData(it)
                    }
                }
            }

            override fun onFailure(call: Call<List<Comment>>, t: Throwable) {
                Toast.makeText(this@PostDetailsActivity, "Failure fetching comments: ${t.message}", Toast.LENGTH_SHORT).show()
            }
        })
    }
}
