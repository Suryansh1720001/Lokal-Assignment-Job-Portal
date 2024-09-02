package com.lokal.jobapp.data.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "jobs")
data class JobResult(
    @PrimaryKey
    val id: Int,
    val advertiser: Int?,
    val amount: String?,
    val button_text: String?,
    val city_location: Int?,
    val company_name: String?,
    val contact_preference: ContactPreference?,
    val content: String?,
    val contentV3: ContentV3?,
    val created_on: String?,
    val creatives: List<Creative>?,
    val custom_link: String?,
    val enable_lead_collection: Boolean?,
    val experience: Int?,
    val expire_on: String?,
    val fb_shares: Int?,
    val fees_charged: Int?,
    val fees_text: String?,
    val is_applied: Boolean?,
    val is_bookmarked: Boolean?,
    val is_job_seeker_profile_mandatory: Boolean?,
    val is_owner: Boolean?,
    val is_premium: Boolean?,
    val job_category: String?,
    val job_category_id: Int?,
    val job_hours: String?,
    val job_location_slug: String?,
    val job_role: String?,
    val job_role_id: Int?,
    val job_tags: List<JobTag>?,
    val job_type: Int?,
    val locality: Int?,
    val locations: List<Location>?,
    val num_applications: Int?,
    val openings_count: Int?,
    val other_details: String?,
    val premium_till: String?,
    val primary_details: PrimaryDetails?,
    val qualification: Int?,
    val salary_max: Int?,
    val salary_min: Int?,
    val shares: Int?,
    val shift_timing: Int?,
    val should_show_last_contacted: Boolean?,
    val status: Int?,
    val title: String?,
    val translated_content: TranslatedContent?,
    val type: Int?,
    val updated_on: String?,
    val views: Int?,
    val whatsapp_no: String?
) : Parcelable