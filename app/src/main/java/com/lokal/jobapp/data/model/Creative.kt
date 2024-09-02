package com.lokal.jobapp.data.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Creative(
    val creative_type: Int?,
    val `file`: String?,
    val image_url: String?,
    val order_id: Int?,
    val thumb_url: String?
) : Parcelable