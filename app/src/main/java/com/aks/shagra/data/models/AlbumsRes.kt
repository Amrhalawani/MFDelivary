package com.aks.shagra.data.models

import com.google.gson.annotations.SerializedName

data class AlbumsRes(

	@field:SerializedName("status_code")
	val statusCode: Int? = null,

	@field:SerializedName("data")
	val data: AlbumsData? = null,

	@field:SerializedName("paginator")
	val paginator: Paginator? = null
)

data class AlbumsByDateItem(

	@field:SerializedName("date")
	val date: String? = null,

	@field:SerializedName("id")
	val id: Int? = null,

	var albums: ArrayList<AlbumDataItem>? = null,

)




data class AlbumsData(

	@field:SerializedName("firstAlbumData")
	val firstAlbumData: ArrayList<AlbumDataItem>? = null,

	@field:SerializedName("albums")
	val albums: ArrayList<AlbumsByDateItem>? = null
)
