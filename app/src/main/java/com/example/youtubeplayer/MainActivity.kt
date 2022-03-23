package com.example.youtubeplayer

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.youtubeplayer.dto.VideoDto
import com.example.youtubeplayer.service.VideoService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentContainer, PlayerFragment())
            .commit()

        getVideoList()
    }


    private fun getVideoList() {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://run.mocky.io/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        retrofit.create(VideoService::class.java).also {
            it.listVideos()
                .enqueue(object: Callback<VideoDto> {
                    override fun onResponse(call: Call<VideoDto>, response: Response<VideoDto>) {
                        // 예외 처리
                        if (response.isSuccessful.not()) {
                            Log.d("MainActivity", "Response Fail....")
                            return
                        }

                        response.body()?.let {
                            Log.d("MainActivity", it.toString())
                        }
                    }

                    override fun onFailure(call: Call<VideoDto>, t: Throwable) {
                        Log.d("MainActivity", "Response Fail....")
                    }
                })
        }

    }
}