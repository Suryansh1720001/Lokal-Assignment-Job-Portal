package com.lokal.jobapp.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.lokal.jobapp.data.local.JobDao
import com.lokal.jobapp.data.model.JobResult
import com.lokal.jobapp.data.remote.ApiService
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class JobRepository @Inject constructor(
    private val apiService: ApiService,
    private val jobDao: JobDao
) {

    fun getJobs(): Flow<PagingData<JobResult>> {
        return Pager(
            config = PagingConfig(
                pageSize = 11,
                maxSize = 100,
                enablePlaceholders = false
            ),
            pagingSourceFactory = { JobsPagingSource(apiService) }
        ).flow
    }

    suspend fun bookmarkJob(job: JobResult) {
        jobDao.insertJob(job)
    }

    fun getBookmarkedJobs(): Flow<List<JobResult>> {
        return jobDao.getAllJobs()
    }

    suspend fun removeBookmark(job: JobResult) {
        jobDao.deleteJob(job.id)
    }

    suspend fun isJobBookmarked(jobId: Int): Boolean {
        return jobDao.isJobBookmarked(jobId)
    }
}





