package com.mohamed.medhat.twograndtask.repository

import com.mohamed.medhat.twograndtask.model.Album
import com.mohamed.medhat.twograndtask.model.Photo
import com.mohamed.medhat.twograndtask.model.User
import com.mohamed.medhat.twograndtask.networking.WebApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject
import kotlin.random.Random

class MainRepository @Inject constructor(private val webApi: WebApi) : Repository {
    private val randomId = (1..10).random(Random(System.currentTimeMillis()))

    override suspend fun getRandomUser(): User {
        return withContext(Dispatchers.IO) {
            val response = webApi.getUsers(randomId)
            if (response.isSuccessful && !response.body().isNullOrEmpty()) {
                response.body()!![0]
            } else {
                throw Exception("Something went wrong!")
            }
        }
    }

    override suspend fun getAlbums(user: User): List<Album> {
        return withContext(Dispatchers.IO) {
            val response = webApi.getAlbums(user.id)
            if (response.isSuccessful && response.body() != null) {
                response.body()!!
            } else {
                throw Exception("Something went wrong!")
            }
        }
    }

    override suspend fun getPhotos(album: Album): List<Photo> {
        return withContext(Dispatchers.IO) {
            val response = webApi.getPhotos(album.id)
            if (response.isSuccessful && response.body() != null) {
                response.body()!!
            } else {
                throw Exception("Something went wrong!")
            }
        }
    }
}