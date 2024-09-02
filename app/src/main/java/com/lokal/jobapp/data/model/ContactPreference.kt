package com.lokal.jobapp.data.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ContactPreference(
    val preference: Int?,
    val preferred_call_end_time: String?,
    val preferred_call_start_time: String?,
    val whatsapp_link: String?
) : Parcelable