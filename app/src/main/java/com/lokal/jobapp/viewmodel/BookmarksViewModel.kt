package com.lokal.jobapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.lokal.jobapp.data.model.JobResult
import com.lokal.jobapp.data.repository.JobRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class BookmarksViewModel @Inject constructor(
    private val jobRepository: JobRepository
) : ViewModel() {

    // Function to get all bookmarked jobs
    fun getBookmarkedJobs(): LiveData<List<JobResult>> {
        return jobRepository.getBookmarkedJobs().asLiveData()
    }
}
