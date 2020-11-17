package com.exercise.gm.houzzexam.feature.main

import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.exercise.gm.houzzexam.feature.RemoteRepository
import com.exercise.gm.houzzexam.network.response.LinkItem

import kotlinx.coroutines.launch

class MainViewModel @ViewModelInject constructor(
    private val remoteRepository: RemoteRepository,
    @Assisted private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _pages: MutableLiveData<List<LinkItem>> = MutableLiveData<List<LinkItem>>()
    val pages: LiveData<List<LinkItem>>
        get() = _pages

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: MutableLiveData<Boolean>
        get() = _isLoading

    fun fetchUrlPages() {
        viewModelScope.launch {
            _isLoading.value = true
            _pages.value = remoteRepository.getAvailablePages().map {
                LinkItem(it.url.replace(INVALID_URL_PREFIX, VALID_URL_PREFIX))
            }
        }
    }

    companion object {
        const val INVALID_URL_PREFIX = "http://"
        const val VALID_URL_PREFIX = "https://"
    }

}