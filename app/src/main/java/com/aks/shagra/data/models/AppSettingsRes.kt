package com.aks.shagra.data.models

import com.google.gson.annotations.SerializedName

data class AppSettingsRes(

    @field:SerializedName("status_code")
    val statusCode: Int? = null,

    @field:SerializedName("data")
    val data: SettingsData? = null,

    @field:SerializedName("message")
    val message: String? = null

)

data class SettingsData(

    @field:SerializedName("image")
    val image: String? = null,

    @field:SerializedName("origin")
    val origin: String? = null,

    @field:SerializedName("originalImage")
    val originalImage: String? = null,

    @field:SerializedName("meeting")
    val meeting: String? = null,

    @field:SerializedName("meeting_date")
    val meeting_date: String? = null,

    @field:SerializedName("live_streaming")
    val live_streaming: String? = null,

    @field:SerializedName("about_us")
    val aboutUs: String? = null,

    @field:SerializedName("contacts")
    val contacts: Contacts? = null,


    )

data class Contacts(
    @field:SerializedName("phone")
    val phone: String? = null,

    @field:SerializedName("email")
    val email: String? = null,
)



