package com.mohamed.medhat.twograndtask.repository

import com.mohamed.medhat.twograndtask.model.Album
import com.mohamed.medhat.twograndtask.model.Photo
import com.mohamed.medhat.twograndtask.model.User

interface Repository {
    suspend fun getRandomUser(): User
    suspend fun getAlbums(user: User): List<Album>
    suspend fun getPhotos(album: Album): List<Photo>
}