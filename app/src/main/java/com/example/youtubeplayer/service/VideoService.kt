package com.example.youtubeplayer.service

import com.example.youtubeplayer.dto.VideoDto
import retrofit2.Call
import retrofit2.http.GET

interface VideoService {
    @GET("/v3/c4be04a5-c1e9-451e-8eab-ba0a8a167f2a")
    fun listVideos(): Call<VideoDto>
}