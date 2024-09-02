package com.lokal.jobapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lokal.jobapp.data.model.JobResult
import com.lokal.jobapp.data.repository.JobRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class JobsDetailViewModel @Inject constructor(
    private val repository: JobRepository
) : ViewModel() {

    fun saveImageIndex(job: JobResult) {
        viewModelScope.launch {
            repository.bookmarkJob(job)
        }
    }

    fun removeBookmark(job: JobResult) {
        viewModelScope.launch {
            repository.removeBookmark(job)
        }
    }

    suspend fun isJobBookmarked(job: JobResult): Boolean {
        return repository.isJobBookmarked(job.id)
    }
}
