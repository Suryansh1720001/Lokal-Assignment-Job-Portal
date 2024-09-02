package com.lokal.jobapp.ui.jobs


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.lokal.jobapp.R
import com.lokal.jobapp.data.model.JobResult

class JobAdapter(
    private val onItemClick: (JobResult) -> Unit
) : PagingDataAdapter<JobResult, JobAdapter.JobsViewHolder>(COMPARATOR) {

    override fun onBindViewHolder(holder: JobsViewHolder, position: Int) {
        val item = getItem(position)
        if (item != null) {
            holder.jobTitle.text = item.title ?: "Job Title"
            holder.locality.text = buildString {
                append("Locality - ")
                append(item.locality.toString() ?: "Unknown")
            }
            holder.min_salary.text = buildString {
                append("Salary Range - ")
                append(item.salary_min.toString() ?: "Unknown")
                append(" - ")
                append(item.salary_max.toString() ?: "Unknown")
            }
            holder.phone.text = buildString {
                append("Phone - ")
                append(item.whatsapp_no ?: "123456789")
            }

            // Set item click listener
            holder.itemView.setOnClickListener {
                onItemClick(item)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): JobsViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_job, parent, false)
        return JobsViewHolder(view)
    }

    class JobsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val jobTitle = itemView.findViewById<TextView>(R.id.titleTextView)
        val locality = itemView.findViewById<TextView>(R.id.tv_locality)
        val min_salary = itemView.findViewById<TextView>(R.id.txt_min_salary)
        val phone = itemView.findViewById<TextView>(R.id.tv_phone)
    }

    companion object {
        private val COMPARATOR = object : DiffUtil.ItemCallback<JobResult>() {
            override fun areItemsTheSame(oldItem: JobResult, newItem: JobResult): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: JobResult, newItem: JobResult): Boolean {
                return oldItem == newItem
            }
        }
    }
}
