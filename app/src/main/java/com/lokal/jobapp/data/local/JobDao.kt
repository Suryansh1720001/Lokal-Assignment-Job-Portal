package com.lokal.jobapp.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.lokal.jobapp.data.model.JobResult
import kotlinx.coroutines.flow.Flow

@Dao
interface JobDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertJob(job: JobResult)

    @Query("SELECT * FROM jobs ORDER BY id ASC")
    fun getAllJobs(): Flow<List<JobResult>>

    @Query("DELETE FROM jobs WHERE id = :jobId")
    suspend fun deleteJob(jobId: Int)

    @Query("SELECT EXISTS(SELECT 1 FROM jobs WHERE id = :jobId)")
    suspend fun isJobBookmarked(jobId: Int): Boolean
}
