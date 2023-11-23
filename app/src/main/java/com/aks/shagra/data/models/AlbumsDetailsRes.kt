package com.aks.shagra.data.models

import com.google.gson.annotations.SerializedName

data class AlbumDetailsRes(

	@field:SerializedName("status_code")
	val statusCode: Int? = null,

	@field:SerializedName("data")
	val data: ArrayList<AlbumDataItem>? = null,

	@field:SerializedName("meta")
	val meta: Meta? = null,

	@field:SerializedName("paginator")
	val paginator: Paginator? = null
)

data class AlbumDataItem(

	@field:SerializedName("thumbnail")
	val thumbnail: String? = null,

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("id")
	val id: Int? = null
)

