package com.example.challenge5.network

import com.example.challenge5.model.GetNowPlayingResponse
import retrofit2.Call
import retrofit2.http.GET

interface ApiService {

    @GET ("movie/now_playing?api_key=a0ca53cb305db9a12b6c99174d34dbd4&language=en-US&page=1")
    fun getNowPlaying(): Call<GetNowPlayingResponse>
}