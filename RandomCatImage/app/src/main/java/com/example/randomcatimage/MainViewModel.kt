package com.example.randomcatimage

import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.bumptech.glide.Glide
import kotlinx.coroutines.*
import retrofit2.Retrofit
import retrofit2.awaitResponse
import retrofit2.converter.gson.GsonConverterFactory

class MainViewModel:ViewModel() {
    private val _imgUrl = MutableLiveData<String>()
    val imgUrl: LiveData<String>
        get() = _imgUrl

    init {
        generateUrl()
    }

    fun generateUrl() {
        val api = Retrofit.Builder()
                .baseUrl("https://api.thecatapi.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(ApiRequests::class.java)

        GlobalScope.launch(Dispatchers.IO) {
            try {
                val response = api.getData().awaitResponse()
                if (response.isSuccessful) {
                    val data = response.body()!!

                    withContext(Dispatchers.Main) {
                        _imgUrl.value = data[0].url
                    }
                }
            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    _imgUrl.value = "error"
                }
            }
        }
    }
    override fun onCleared() {
        GlobalScope.cancel()
        super.onCleared()
    }
}