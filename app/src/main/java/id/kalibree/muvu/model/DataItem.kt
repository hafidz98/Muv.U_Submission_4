package id.kalibree.muvu.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class DataItem(
    var poster: Int,
    var title: String?,
    var desc: String?
) : Parcelable