package com.mohamed.medhat.twograndtask.networking

import com.mohamed.medhat.twograndtask.model.Album
import com.mohamed.medhat.twograndtask.model.Photo
import com.mohamed.medhat.twograndtask.model.User
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface WebApi {
    @GET("/users")
    suspend fun getUsers(@Query("id") id: Int): Response<List<User>>

    @GET("/albums")
    suspend fun getAlbums(@Query("userId") userId: Int): Response<List<Album>>

    @GET("/photos")
    suspend fun getPhotos(@Query("albumId") albumId: Int): Response<List<Photo>>
}