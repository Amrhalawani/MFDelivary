package com.aks.shagra.data.models

import com.google.gson.annotations.SerializedName

data class NewBornCasesRes(

    @field:SerializedName("status_code")
    val statusCode: Int? = null,

    @field:SerializedName("data")
    val data: ArrayList<NewBornItem>? = null,

    @field:SerializedName("paginator")
    val paginator: Paginator? = null,

    @field:SerializedName("message")
    val message: String? = null,
)


data class NewBornItem(

    @field:SerializedName("id")
    val id: Int? = null,

    @field:SerializedName("name")
    val name: String? = null,

    @field:SerializedName("birthdate")
    val birthdate: String? = null,

    @field:SerializedName("status")
    val status: String? = null,

    @field:SerializedName("status_code")
    val status_code: String? = null,

)