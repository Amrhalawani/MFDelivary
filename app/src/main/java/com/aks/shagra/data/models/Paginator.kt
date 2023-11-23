package com.aks.shagra.data.models

import com.google.gson.annotations.SerializedName

data class Paginator(

	@field:SerializedName("next_page")
	val nextPage: Any? = null,

	@field:SerializedName("per_page")
	val perPage: Int? = null,

	@field:SerializedName("total")
	val total: Int? = null,

	@field:SerializedName("pages")
	val pages: Int? = null,

	@field:SerializedName("next_page_url")
	val nextPageUrl: Any? = null,

	@field:SerializedName("prev_page_url")
	val prevPageUrl: Any? = null,

	@field:SerializedName("prev_page")
	val prevPage: Any? = null,

	@field:SerializedName("current_page")
	val currentPage: Int? = null
)