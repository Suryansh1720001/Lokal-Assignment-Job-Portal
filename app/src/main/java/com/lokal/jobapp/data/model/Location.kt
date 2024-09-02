package com.lokal.jobapp.data.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Location(
    val id: Int,
    val locale: String,
    val state: Int
) : Parcelable