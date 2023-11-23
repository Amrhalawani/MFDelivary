package com.aks.shagra.data.models

import com.google.gson.annotations.SerializedName

data class NewsRes(

	@field:SerializedName("status_code")
	val statusCode: Int? = null,

	@field:SerializedName("data")
	val data: Data? = null,

	@field:SerializedName("paginator")
	val paginator: Paginator? = null
){
	data class Data(

		@field:SerializedName("news")
		val news: ArrayList<NewsItem>? = null,

		@field:SerializedName("categories")
		val categories: ArrayList<CategoriesItem>? = null
	)

}

