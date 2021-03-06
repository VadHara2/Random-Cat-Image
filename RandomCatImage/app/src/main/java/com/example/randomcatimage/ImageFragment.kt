package com.example.randomcatimage

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.example.randomcatimage.databinding.FragmentImageBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

lateinit var imageBinding: FragmentImageBinding
class ImageFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var currentUrl:String =" "
        val viewModel = MainViewModel()
        imageBinding = FragmentImageBinding.inflate(inflater)
        imageBinding.viewmodel = viewModel
        imageBinding.progressBar.visibility = View.VISIBLE
        imageBinding.layoutImg.setOnClickListener {
            imageBinding.progressBar.visibility = View.VISIBLE
            viewModel.generateUrl()
        }

        viewModel.imgUrl.observe(viewLifecycleOwner, Observer {
            if (it == "error"){
                Toast.makeText(activity, "Seems like something went wrong...", Toast.LENGTH_SHORT)
            }else {
                currentUrl = it
                setImg(it)}
        })

        imageBinding.floatingActionButton.setOnClickListener {
            findNavController().navigate(ImageFragmentDirections.actionImageFragmentToDialog(currentUrl))
        }

        return imageBinding.root
    }
    fun setImg(url:String){
        Glide.with(this).load(url).into(imageBinding.imageView)
        GlobalScope.launch(Dispatchers.Main) {
            delay(2000)
            imageBinding.progressBar.visibility =View.GONE
        }
    }
}