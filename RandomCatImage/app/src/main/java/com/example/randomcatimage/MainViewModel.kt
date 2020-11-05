package com.example.randomcatimage

import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.bumptech.glide.Glide
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
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

    fun generateUrl(){
//        binding.progressBar.visibility = View.VISIBLE
        val api = Retrofit.Builder()
            .baseUrl("https://api.thecatapi.com")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiRequests::class.java)

        GlobalScope.launch(Dispatchers.IO) {
            val response = api.getData().awaitResponse()
            if (response.isSuccessful){
                val data = response.body()!!

                withContext(Dispatchers.Main){
//                    binding.progressBar.visibility = View.GONE
                    _imgUrl.value = data[0].url
                }
            }

        }
    }
}