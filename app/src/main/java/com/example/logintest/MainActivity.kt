package com.example.logintest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.example.logintest.Models.Answer
import com.example.logintest.Models.User
import com.example.logintest.Retrofit.RetrofitApi
import com.google.gson.Gson
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.*
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var login: EditText = findViewById(R.id.editTextTextPersonName)
        var password: EditText = findViewById(R.id.editTextTextPersonName2)
        var signin: Button = findViewById(R.id.button)
        signin.setOnClickListener{
            logIn(login.text.toString(), password.text.toString())
        }
    }
    private fun logIn(login:String, password:String)
    {
        val inteceptor = HttpLoggingInterceptor()
        inteceptor.level = HttpLoggingInterceptor.Level.BODY

        val client = OkHttpClient.Builder().addInterceptor(inteceptor).build()

        val retrofit = Retrofit.Builder().baseUrl("http://185.221.214.178:8888").client(client).addConverterFactory(GsonConverterFactory.create()).build()
        val retrofitApi:RetrofitApi = retrofit.create(RetrofitApi::class.java)
        val call: Call<Answer> = retrofitApi.login(
            User(
                username = login,
                password =  password
            )
        )
        call.enqueue(object :Callback<Answer?>{
            override fun onResponse(call: Call<Answer?>, response: Response<Answer?>) {
                Log.d("a", "Success")
                Log.d("a", response.body().toString())
                val responseFromApi = response.body()
                Log.d("a", responseFromApi.toString())
            }

            override fun onFailure(call: Call<Answer?>, t: Throwable) {
                Log.d("a", "Error")
            }
        })

    }
}