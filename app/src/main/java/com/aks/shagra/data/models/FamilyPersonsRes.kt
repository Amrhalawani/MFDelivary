package com.aks.shagra.data.models

import com.google.gson.annotations.SerializedName

data class FamilyPersonsRes(

    @field:SerializedName("status_code")
    val statusCode: Int? = null,

    @field:SerializedName("data")
    val data: FamilyPersonsData? = null,

    @field:SerializedName("paginator")
    val paginator: Paginator? = null
)


data class LatestCoursesItem(

    @field:SerializedName("image")
    val image: String? = null,

    @field:SerializedName("id")
    val id: Int? = null
)

data class PositionsItem(

    @field:SerializedName("name")
    val name: String? = null,

    @field:SerializedName("id")
    val id: Int? = null,

    var categories: ArrayList<User>? = null,
)

data class FamilyPersonsData(

    @field:SerializedName("notables")
    val notables: ArrayList<User>? = null,

    @field:SerializedName("positions")
    val positions: ArrayList<PositionsItem>? = null

)
