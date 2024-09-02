package com.lokal.jobapp.ui.bookmarks

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.lokal.jobapp.data.model.JobResult
import com.lokal.jobapp.databinding.ItemJobBinding


class BookmarksAdapter : RecyclerView.Adapter<BookmarksAdapter.BookmarksViewHolder>() {

    private var jobList: List<JobResult> = listOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookmarksViewHolder {
        val binding = ItemJobBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return BookmarksViewHolder(binding)
    }

    override fun onBindViewHolder(holder: BookmarksViewHolder, position: Int) {
        val job = jobList[position]
        holder.bind(job)
    }

    override fun getItemCount(): Int = jobList.size

    fun submitList(jobs: List<JobResult>) {
        jobList = jobs
        notifyDataSetChanged()
    }

    class BookmarksViewHolder(private val binding: ItemJobBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(job: JobResult) {
            binding.titleTextView.text = job.title
            binding.tvLocality.text = job.locality.toString()
            binding.txtMinSalary.text = job.salary_min.toString()
            binding.tvPhone.text = job.whatsapp_no
        }
    }
}
