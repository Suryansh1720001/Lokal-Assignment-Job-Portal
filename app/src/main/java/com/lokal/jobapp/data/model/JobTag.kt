package com.lokal.jobapp.data.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class JobTag(
    val bg_color: String?,
    val text_color: String?,
    val value: String?
) : Parcelable