package com.example.randomcatimage

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.renderscript.ScriptGroup
import android.util.Log
import android.view.View
import android.widget.ImageView
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
        val viewModel = MainViewModel()
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.viewmodel = viewModel
        binding.progressBar.visibility = View.VISIBLE
        binding.layoutMain.setOnClickListener {
            binding.progressBar.visibility = View.VISIBLE
            viewModel.generateUrl()
        }

        viewModel.imgUrl.observe(this, Observer {
            setImg(it)
        })
    }
    fun setImg(url:String){
        Glide.with(this).load(url).centerCrop().into(binding.imageView)
        GlobalScope.launch(Dispatchers.Main) {
//            binding.imageView.visibility = View.GONE
            delay(2000)
            binding.progressBar.visibility =View.GONE
//            binding.imageView.visibility = View.VISIBLE
        }

    }
}