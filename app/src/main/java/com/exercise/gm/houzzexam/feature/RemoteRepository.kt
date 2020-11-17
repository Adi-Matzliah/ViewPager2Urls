package com.exercise.gm.houzzexam.feature

import com.exercise.gm.houzzexam.network.HouzzApi
import javax.inject.Inject

class RemoteRepository @Inject constructor(private val api: HouzzApi) {
    suspend fun getAvailablePages() =
        api.getPages().items
}