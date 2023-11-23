package com.aks.shagra.data.models

import com.google.gson.annotations.SerializedName

data class GalleryRes(

	@field:SerializedName("status_code")
	val statusCode: Int? = null,

	@field:SerializedName("data")
	val data: ArrayList<GalleryItem>? = null,

	@field:SerializedName("meta")
	val meta: Meta? = null,

	@field:SerializedName("paginator")
	val paginator: Paginator? = null
)

data class GalleryItem(

	@field:SerializedName("thumbnail")
	val thumbnail: String? = null,

	@field:SerializedName("filesCount")
	val filesCount: Int? = null,

	@field:SerializedName("albumsCount")
	val albumsCount: Int? = null,

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("type")
	val type: String? = null
)

data class Meta(

	@field:SerializedName("pagination")
	val pagination: Pagination? = null
)

data class Pagination(

	@field:SerializedName("per_page")
	val perPage: Int? = null,

	@field:SerializedName("total")
	val total: Int? = null,

	@field:SerializedName("count")
	val count: Int? = null,

	@field:SerializedName("links")
	val links: Links? = null,

	@field:SerializedName("total_pages")
	val totalPages: Int? = null,

	@field:SerializedName("current_page")
	val currentPage: Int? = null
)

data class Links(
	val any: Any? = null
)
