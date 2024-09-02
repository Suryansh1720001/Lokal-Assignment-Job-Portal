package com.lokal.jobapp.data.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class V3(
    val field_key: String?,
    val field_name: String?,
    val field_value: String?
) : Parcelable