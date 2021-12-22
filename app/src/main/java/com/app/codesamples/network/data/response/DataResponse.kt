package com.app.codesamples.network.data.response

import com.app.codesamples.network.domain.model.DataItem
import java.util.*
import com.google.gson.annotations.SerializedName


data class DataResponse (
    @SerializedName("id")
    val id: Int,
    @SerializedName("firstname")
    val first: String = "",
    @SerializedName("lastname")
    val last: String = "",
    @SerializedName("photoUrl")
    val photoUrl: String = "",
    @SerializedName("about")
    val about: String = "")

    fun DataResponse.toDataItem() = DataItem(
        first,
        last,
        photoUrl,
        about
)

