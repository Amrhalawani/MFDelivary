package com.aks.shagra.data.models

import com.google.gson.annotations.SerializedName

data class DefaultRes(

	@field:SerializedName("status_code")
	val statusCode: Int? = null,

	@field:SerializedName("message")
	val message: String? = null

)