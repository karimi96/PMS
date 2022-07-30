package com.karimi.pms.api

import com.karimi.pms.modal.test.Data
import retrofit2.Call
import retrofit2.http.GET

interface ApiService {
    @GET("posts")
    fun getPost(): Call<Data>

}