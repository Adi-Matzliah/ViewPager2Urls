package com.exercise.gm.houzzexam.di

import com.exercise.gm.houzzexam.network.HouzzApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import dagger.hilt.android.scopes.ActivityRetainedScoped
import retrofit2.Retrofit

@InstallIn(ActivityRetainedComponent::class)
@Module
object MainModule {
    @Provides
    @ActivityRetainedScoped
     fun provideMainApi(retrofit: Retrofit): HouzzApi = retrofit.create(HouzzApi::class.java)
}