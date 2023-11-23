package com.aks.shagra.data.models

import com.google.gson.annotations.SerializedName

data class AlbumDetailsFilesRes(

	@field:SerializedName("status_code")
	val statusCode: Int? = null,

	@field:SerializedName("data")
	val data: AlbumData? = null,

	@field:SerializedName("paginator")
	val paginator: Paginator? = null
)

data class AlbumData(

	@field:SerializedName("album")
	val album: AlbumDataItem? = null,

	@field:SerializedName("files")
	val files: Files? = null
)


data class FilesDataItem(

	@field:SerializedName("file")
	val file: String? = null,

	@field:SerializedName("id")
	val id: Int? = null,
	@field:SerializedName("type")
	val type: String? = null,

)



data class Files(

	@field:SerializedName("data")
	val data: ArrayList<FilesDataItem>? = null
)

