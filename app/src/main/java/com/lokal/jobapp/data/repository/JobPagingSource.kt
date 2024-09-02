package com.lokal.jobapp.data.repository

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.google.gson.Gson
import com.lokal.jobapp.data.model.JobResult
import com.lokal.jobapp.data.remote.ApiService

class JobsPagingSource(
    private val apiService: ApiService
) : PagingSource<Int, JobResult>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, JobResult> {
        return try {
            val position = params.key ?: 1

            val response = apiService.getJobs(position)

            if (response.results == null) {
                return LoadResult.Error(Exception("Response result is null"))
            }

            val jobs = response.results

            LoadResult.Page(
                data = response.results,
                prevKey = if (position == 1) null else position - 1,
                nextKey = if (jobs.isEmpty()) null else position + 1
            )
        } catch (exception: Exception) {
            LoadResult.Error(exception)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, JobResult>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }


}

