package com.testing.myapplication

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.testing.myapplication.adapter.GitAdapter
import com.testing.myapplication.databinding.ActivityMainBinding
import com.testing.myapplication.viewModal.GitViewModal


class MainActivity : AppCompatActivity() {

    private lateinit var gitViewModal: GitViewModal
    private var _binding: ActivityMainBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        val view: View = binding.root
        setContentView(view)
        binding.retry.setOnClickListener {
            initApiCall()
        }
        initApiCall()
    }

    private fun initApiCall() {
        gitViewModal = ViewModelProvider(this).get(GitViewModal::class.java)
        gitViewModal.loadingState.observe(this, androidx.lifecycle.Observer {
            binding.shimmer.visibility = View.VISIBLE
            binding.shimmer.startShimmer()
            binding.recyclerView.visibility = View.GONE
            binding.animationConstraint.visibility = View.GONE
            when (it) {
                0 -> {
                    binding.animationConstraint.visibility = View.GONE
                    binding.shimmer.startShimmer()
                }
                1 -> {
                    binding.shimmer.stopShimmer()
                    binding.shimmer.visibility = View.GONE
                    binding.totalCount.visibility = View.VISIBLE
                    binding.recyclerView.visibility = View.VISIBLE
                }
                2 -> {
                    binding.shimmer.visibility = View.GONE
                    binding.animationConstraint.visibility = View.VISIBLE
                }
            }
        })
        gitViewModal.getData()



        gitViewModal.dataList.observe(this, androidx.lifecycle.Observer {
            val myAdapter = GitAdapter(it.items)
            binding.totalCount.text = "Total Trending Repositories Count: ${it.items.size}"
            myAdapter.notifyDataSetChanged()
            binding.recyclerView.adapter = myAdapter
        })
    }


}