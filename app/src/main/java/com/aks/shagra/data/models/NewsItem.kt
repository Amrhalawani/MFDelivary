package com.aks.shagra.data.models

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class NewsItem(

    @field:SerializedName("thumbnail")
    val thumbnail: String? = null,

    @field:SerializedName("releaseDate")
    val releaseDate: String? = null,

    @field:SerializedName("icon")
    val icon: String? = null,

    @field:SerializedName("id")
    val id: Int? = null,

    @field:SerializedName("title")
    val title: String? = null,

    @field:SerializedName("category")
    val category: CategoriesItem? = null,

    @field:SerializedName("images")
    val images: ArrayList<ImageItem>? = null,

    @field:SerializedName("content")
    val content: Content? = null,
    @field:SerializedName("view")
    val view: String? = null,
    @field:SerializedName("type")
    val type: String? = null
) : Parcelable

@Parcelize
data class Content(

    @field:SerializedName("description")
    val description: String? = null,

    @field:SerializedName("brideName")
    val brideName: String? = null,

    @field:SerializedName("groomName")
    val groomName: String? = null
) : Parcelable

@Parcelize
data class ImageItem(

    @field:SerializedName("id")
    val id: Int? = null,

    @field:SerializedName("image")
    val image: String? = null
) : Parcelable
