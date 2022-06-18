package com.mohamed.medhat.twograndtask.ui.activities.main

import android.util.Log
import androidx.lifecycle.*
import com.mohamed.medhat.twograndtask.di.MainRepo
import com.mohamed.medhat.twograndtask.model.Album
import com.mohamed.medhat.twograndtask.model.User
import com.mohamed.medhat.twograndtask.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

private const val TAG = "MainViewModel"

@HiltViewModel
class MainViewModel @Inject constructor(@MainRepo private val repository: Repository) :
    ViewModel() {

    private val _user = MutableLiveData<User>()
    val user: LiveData<User>
        get() = _user

    private val mediatorObserver = Observer<User> {
        Log.d(TAG, "mediator: $it")
        viewModelScope.launch {
            try {
                val albumsResult = repository.getAlbums(it)
                _albums.postValue(albumsResult)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    private val _albums = MutableLiveData<List<Album>>()
    val albums: LiveData<List<Album>>
        get() = _albums

    init {
        user.observeForever(mediatorObserver)
        viewModelScope.launch {
            try {
                val userResult = repository.getRandomUser()
                _user.postValue(userResult)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        user.removeObserver(mediatorObserver)
    }
}