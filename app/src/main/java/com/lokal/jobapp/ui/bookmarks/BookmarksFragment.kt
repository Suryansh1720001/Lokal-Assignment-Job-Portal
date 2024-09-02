package com.lokal.jobapp.ui.bookmarks

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.lokal.jobapp.databinding.FragmentBookmarksBinding
import com.lokal.jobapp.ui.base.BaseFragment
import com.lokal.jobapp.viewmodel.BookmarksViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class BookmarksFragment : BaseFragment<FragmentBookmarksBinding>() {

    private val bookmarksViewModel: BookmarksViewModel by viewModels()
    private lateinit var bookmarksAdapter: BookmarksAdapter

    override fun inflateBinding(): FragmentBookmarksBinding {
        return FragmentBookmarksBinding.inflate(layoutInflater)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        observeViewModel()
    }

    private fun setupRecyclerView() {
        bookmarksAdapter = BookmarksAdapter()
        binding.rvBookmarkJobs.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = bookmarksAdapter
        }
    }

    private fun observeViewModel() {
        bookmarksViewModel.getBookmarkedJobs().observe(viewLifecycleOwner) { jobs ->
            if (jobs.isEmpty()) {
                binding.tvNothingFound.visibility = View.VISIBLE
                binding.lottieNothingFound.visibility = View.VISIBLE
                binding.rvBookmarkJobs.visibility = View.GONE
            } else {
                binding.tvNothingFound.visibility = View.GONE
                binding.lottieNothingFound.visibility = View.GONE
                binding.rvBookmarkJobs.visibility = View.VISIBLE
                bookmarksAdapter.submitList(jobs)
            }
        }
    }
}

