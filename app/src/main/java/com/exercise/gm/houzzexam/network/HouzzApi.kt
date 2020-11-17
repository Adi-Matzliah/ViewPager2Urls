package com.exercise.gm.houzzexam.network

import com.exercise.gm.houzzexam.network.response.LinkItemResponse
import retrofit2.http.GET

interface HouzzApi {

    @GET("pages")
    suspend fun getPages(): LinkItemResponse
}

