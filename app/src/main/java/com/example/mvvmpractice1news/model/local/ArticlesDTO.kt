package com.example.mvvmpractice1news.model.local

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class ArticlesDTO(

	@field:SerializedName("source")
	val source: SourceDTO? = null,

	@field:SerializedName("author")
	val author: Any? = null,

	@field:SerializedName("title")
	val title: String? = null,

	@field:SerializedName("description")
	val description: String? = null,

	@field:SerializedName("url")
	val url: String? = null,

	@field:SerializedName("urlToImage")
	val urlToImage: String? = null,

	@field:SerializedName("publishedAt")
	val publishedAt: String? = null,

	@field:SerializedName("content")
	val content: String? = null
)