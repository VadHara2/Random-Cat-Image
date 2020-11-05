package com.example.randomcatimage

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.renderscript.ScriptGroup
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatDelegate
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import com.example.randomcatimage.databinding.ActivityMainBinding
import kotlinx.coroutines.*
import retrofit2.Retrofit
import retrofit2.awaitResponse
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

lateinit var binding: ActivityMainBinding
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        val viewModel = MainViewModel()
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.viewmodel = viewModel
        binding.progressBar.visibility = View.VISIBLE
        binding.layoutMain.setOnClickListener {
            binding.progressBar.visibility = View.VISIBLE
            viewModel.generateUrl()
        }

        viewModel.imgUrl.observe(this, Observer {
            if (it == "error"){
                Toast.makeText(applicationContext, "Seems like something went wrong...", Toast.LENGTH_SHORT)
            }else setImg(it)
        })
    }
    fun setImg(url:String){
        Glide.with(this).load(url).into(binding.imageView)
        GlobalScope.launch(Dispatchers.Main) {
            delay(2000)
            binding.progressBar.visibility =View.GONE
        }
    }
}