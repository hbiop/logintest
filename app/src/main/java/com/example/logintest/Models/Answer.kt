package com.example.logintest.Models

import com.google.gson.JsonObject
import com.google.gson.annotations.SerializedName

data class Answer(
    val notice:Notice?
)

data class Notice(
    @SerializedName("token")
    val token: String
)
