package com.example.mvvmpractice1news.model.local

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class ResponseDTO(

	@field:SerializedName("status")
	val status: String? = null,

	@field:SerializedName("totalResults")
	val totalResults: Int? = null,

	@field:SerializedName("articles")
	val articles: List<ArticlesDTO?>? = null
)