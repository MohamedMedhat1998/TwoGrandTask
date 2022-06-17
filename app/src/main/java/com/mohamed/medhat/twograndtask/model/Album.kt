package com.mohamed.medhat.twograndtask.model

import com.google.gson.annotations.SerializedName

data class Album(

	@field:SerializedName("id")
	val id: Int,

	@field:SerializedName("title")
	val title: String,

	@field:SerializedName("userId")
	val userId: Int
)
