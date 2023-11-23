package com.aks.shagra.data.models

import com.google.gson.annotations.SerializedName

data class CertificationsRes(

	@field:SerializedName("status_code")
	val statusCode: Int? = null,

	@field:SerializedName("data")
	val data: ArrayList<ImageItem>? = null,

	@field:SerializedName("message")
	val message: String? = null
){

}

