package com.mohamed.medhat.twograndtask.ui.activities.album

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mohamed.medhat.twograndtask.di.MainRepo
import com.mohamed.medhat.twograndtask.model.Album
import com.mohamed.medhat.twograndtask.model.Photo
import com.mohamed.medhat.twograndtask.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AlbumViewModel @Inject constructor(@MainRepo private val repository: Repository) :
    ViewModel() {

    private val _photos = MutableLiveData<List<Photo>>()
    val photos: LiveData<List<Photo>>
        get() = _photos

    fun loadPhotos(albumId: Int) {
        viewModelScope.launch {
            try {
                val photosResult = repository.getPhotos(albumId)
                _photos.postValue(photosResult)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}