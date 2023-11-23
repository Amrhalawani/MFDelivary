package com.aks.shagra.data.models

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

data class LoginRes(

	@field:SerializedName("authorization")
	val authorization: Authorization? = null,

	@field:SerializedName("status_code")
	val statusCode: Int? = null,

	@field:SerializedName("user")
	val user: User? = null,
	@field:SerializedName("message")
	val message: String? = null
)

data class Authorization(

	@field:SerializedName("type")
	val type: String? = null,

	@field:SerializedName("token")
	val token: String? = null
)

@Parcelize
data class User(

	@field:SerializedName("code")
	val code: String? = null,

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("has_more_courses")
	val has_more_courses: Boolean? = null,

	@field:SerializedName("id")
	val id: Int? = null,


	@field:SerializedName("avatar")
	val avatar: String? = null,

	@field:SerializedName("job")
	val job: String? = null,

	@field:SerializedName("mother_name")
	val mother_name: String? = null,

	@field:SerializedName("address")
	val address: String? = null,

	@field:SerializedName("about")
	val about: String? = null


): Parcelable {

}

