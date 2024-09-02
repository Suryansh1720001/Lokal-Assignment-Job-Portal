package com.lokal.jobapp.ui.jobs

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.lokal.jobapp.databinding.FragmentJobsBinding
import com.lokal.jobapp.ui.base.BaseFragment
import com.lokal.jobapp.ui.jobDetailsActicity.JobsDetailActivity
import com.lokal.jobapp.utils.Resource
import com.lokal.jobapp.viewmodel.JobsViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class JobsFragment : BaseFragment<FragmentJobsBinding>() {

    private val jobsViewModel: JobsViewModel by viewModels()
    private lateinit var jobAdapter: JobAdapter

    override fun inflateBinding(): FragmentJobsBinding {
        return FragmentJobsBinding.inflate(layoutInflater)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        binding.mainProgressBar.visibility = View.VISIBLE

        jobAdapter = JobAdapter { jobResult ->
            val intent = Intent(context, JobsDetailActivity::class.java).apply {
                putExtra("jobResult", jobResult) // `jobResult` should be Parcelable
            }
            startActivity(intent)
        }
        setupRecyclerView()
        observeJobs()
    }

    private fun setupRecyclerView() {
        val recyclerView = binding.recyclerView
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.setHasFixedSize(true)
        recyclerView.adapter = jobAdapter
    }

    private fun observeJobs() {
        viewLifecycleOwner.lifecycleScope.launch {
            jobsViewModel.jobs.collect { resource ->
                when (resource) {
                    is Resource.Loading -> {
                        // Show loading indicator
                        binding.mainProgressBar.visibility = View.VISIBLE
                        Toast.makeText(context, "Loading", Toast.LENGTH_SHORT).show()
                    }

                    is Resource.Success -> {
                        // Hide loading indicator and submit data to the adapter
                        binding.mainProgressBar.visibility = View.GONE
                        resource.data?.let { pagingData ->
                            jobAdapter.submitData(pagingData)
                        }
                    }

                    is Resource.Error -> {
                        // Hide loading indicator and show error message
                        binding.mainProgressBar.visibility = View.GONE
                        Toast.makeText(
                            context,
                            resource.message ?: "An error occurred",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
            }
        }
    }
}

