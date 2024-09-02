package com.lokal.jobapp.ui.jobDetailsActicity

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.ImageView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.Glide
import com.lokal.jobapp.R
import com.lokal.jobapp.data.model.JobResult
import com.lokal.jobapp.databinding.ActivityJobsDetailActivitiyBinding
import com.lokal.jobapp.viewmodel.JobsDetailViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch


@AndroidEntryPoint
class JobsDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityJobsDetailActivitiyBinding
    private lateinit var viewModel: JobsDetailViewModel
    private lateinit var bookmarkImageView: ImageView

    private var jobResult: JobResult? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityJobsDetailActivitiyBinding.inflate(layoutInflater)
        setContentView(binding.root)
        bookmarkImageView = binding.icBookmark

        viewModel = ViewModelProvider(this).get(JobsDetailViewModel::class.java)

        jobResult = intent.getParcelableExtra("jobResult")
        jobResult?.let {
            populateJobDetails(it)
            checkBookmarkStatus(it)
        }

        bookmarkImageView.setOnClickListener {
            lifecycleScope.launch {
                val isBookmarked = jobResult?.let { it1 -> viewModel.isJobBookmarked(it1) }
                if (isBookmarked!!) {
                    jobResult?.let { it1 -> viewModel.removeBookmark(it1) }
                } else {
                    jobResult?.let { it1 -> viewModel.saveImageIndex(it1) }
                }
                updateBookmarkIcon(!isBookmarked)
            }
        }

    }

    private fun populateJobDetails(it: JobResult) {
        val imageUrl = it.creatives?.firstOrNull()?.file
            ?: "https://media.istockphoto.com/id/1418442424/vector/we-are-hiring-join-our-team-speech-bubbles-banner-vector-design-template.jpg?s=612x612&w=0&k=20&c=H3--0ZYCCPp1S7RIH2Sq-0V1koANuy7gdKVbXRhsR-0="
        if (!imageUrl.isNullOrEmpty()) {
            Glide.with(this)
                .load(imageUrl)
                .placeholder(R.drawable.placeholder)
                .error(R.drawable.alter_image)
                .into(binding.jobImage)
        }
        binding.jobTitle.text = it.title ?: "Job Title"
        val whatsapp_no = it.whatsapp_no ?: "1234567890"

        binding.jobLocation.text = "Place: ${it.locations?.firstOrNull()?.locale ?: "Unknown"}"
        binding.jobSalary.text = "Salary: ${it.primary_details?.Salary}"
        binding.jobType.text = "Job Type: ${it.primary_details?.Job_Type}"
        binding.jobExperience.text = "Experience: ${it.primary_details?.Experience}"
        binding.jobQualification.text = "Qualification: ${it.primary_details?.Qualification}"

        binding.jobTags.text = "${it.openings_count} Vacancies"

        binding.whatsappButton.setOnClickListener {
            val whatsappUri = "https://api.whatsapp.com/send?phone=${whatsapp_no}"
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(whatsappUri))
            startActivity(intent)
        }

        binding.callHrButton.setOnClickListener {
            val callUri = "tel:${whatsapp_no}"
            val intent = Intent(Intent.ACTION_DIAL, Uri.parse(callUri))
            startActivity(intent)
        }
    }

    private fun checkBookmarkStatus(job: JobResult) {
        lifecycleScope.launch {
            val isBookmarked = viewModel.isJobBookmarked(job)
            updateBookmarkIcon(isBookmarked)
        }
    }

    private fun updateBookmarkIcon(isBookmarked: Boolean) {
        val drawableRes =
            if (isBookmarked) R.drawable.ic_remove_bookmark else R.drawable.ic_apply_bookmark
        bookmarkImageView.setImageResource(drawableRes)
    }
}
