package com.aks.shagra.data.models

import com.google.gson.annotations.SerializedName

data class FamilyPersonsOneItemRes(

	@field:SerializedName("status_code")
	val statusCode: Int? = null,

	@field:SerializedName("data")
	val data: ArrayList<User>? = null
)

