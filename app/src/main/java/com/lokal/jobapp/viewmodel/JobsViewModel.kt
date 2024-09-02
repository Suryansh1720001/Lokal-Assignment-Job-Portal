package com.lokal.jobapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.lokal.jobapp.data.model.JobResult
import com.lokal.jobapp.data.repository.JobRepository
import com.lokal.jobapp.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class JobsViewModel @Inject constructor(
    private val jobRepository: JobRepository
) : ViewModel() {


    private val _jobs = MutableStateFlow<Resource<PagingData<JobResult>>>(Resource.Loading())
    val jobs: StateFlow<Resource<PagingData<JobResult>>> get() = _jobs

    init {
        fetchJobs()
    }

    private fun fetchJobs() {
        viewModelScope.launch {
            jobRepository.getJobs()
                .cachedIn(viewModelScope)
                .collect { pagingData ->
                    _jobs.value = Resource.Success(pagingData)
                }
        }
    }
}

