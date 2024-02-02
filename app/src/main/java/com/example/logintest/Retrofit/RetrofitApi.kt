package com.example.logintest.Retrofit

import com.example.logintest.Models.Answer
import com.example.logintest.Models.User
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.FormUrlEncoded
import retrofit2.http.Headers
import retrofit2.http.POST

interface RetrofitApi {
    @Headers("content-type: application/json")

    @POST("/login/")
    fun login(@Body user: User): Call<Answer>
}