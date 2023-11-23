package com.aks.shagra.data.models

import com.google.gson.annotations.SerializedName

data class DeathCasesRes (

    @field:SerializedName("status_code")
    val statusCode: Int? = null,

    @field:SerializedName("data")
    val data: ArrayList<DeathItem>? = null,

    @field:SerializedName("paginator")
    val paginator: Paginator? = null,

    @field:SerializedName("message")
    val message: String? = null,

)


data class DeathItem(

    @field:SerializedName("id")
    val id: Int? = null,

    @field:SerializedName("name")
    val name: String? = null,

    @field:SerializedName("death_date")
    val death_date: String? = null,

    @field:SerializedName("relation")
    val relation: String? = null,

    @field:SerializedName("status")
    val status: String? = null,

    @field:SerializedName("burial_location")
    val burial_location: BurialLocation? = null,


    @field:SerializedName("status_code")
    val status_code: String? = null,

)

data class BurialLocation(

    @field:SerializedName("name")
    val name: String? = null,

    @field:SerializedName("longitude")
    val longitude: String? = null,

    @field:SerializedName("latitude")
    val latitude: String? = null,


)