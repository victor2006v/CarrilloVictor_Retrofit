package com.example.carrillovictor_retrofit

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.carrillovictor_retrofit.adapter.PostAdapter
import com.example.carrillovictor_retrofit.databinding.ActivityPostsBinding
import com.example.carrillovictor_retrofit.model.Post
import com.example.carrillovictor_retrofit.network.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PostsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityPostsBinding
    private lateinit var postAdapter: PostAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPostsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupRecyclerView()

        binding.btnFetchPosts.setOnClickListener {
            val input = binding.etNumber.text.toString()
            if (input.isNotEmpty()) {
                fetchPosts()
            } else {
                Toast.makeText(this, "Please enter an integer", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun setupRecyclerView() {
        postAdapter = PostAdapter(emptyList())
        binding.rvPosts.layoutManager = LinearLayoutManager(this)
        binding.rvPosts.adapter = postAdapter
    }

    private fun fetchPosts() {
        RetrofitClient.apiService.getPosts().enqueue(object : Callback<List<Post>> {
            override fun onResponse(call: Call<List<Post>>, response: Response<List<Post>>) {
                if (response.isSuccessful) {
                    response.body()?.let {
                        postAdapter.updateData(it)
                    }
                } else {
                    Toast.makeText(this@PostsActivity, "Error: ${response.code()}", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<List<Post>>, t: Throwable) {
                Toast.makeText(this@PostsActivity, "Failure: ${t.message}", Toast.LENGTH_SHORT).show()
            }
        })
    }
}
