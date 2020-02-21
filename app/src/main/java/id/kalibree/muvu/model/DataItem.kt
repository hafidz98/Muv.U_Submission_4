package id.kalibree.muvu.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class DataItem(
    var id: Int = 0,
    var poster: String? = null,
    var title: String? = null,
    var desc: String? = null,
    var release_date: String? = null,
    var score: Double = 0.0
) : Parcelable