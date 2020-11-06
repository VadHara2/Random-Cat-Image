package com.example.randomcatimage

import android.app.DownloadManager
import android.content.Context.DOWNLOAD_SERVICE
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat.getSystemService
import androidx.navigation.fragment.navArgs
import com.example.randomcatimage.databinding.DialogBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class Dialog: BottomSheetDialogFragment(){
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.dialog, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val args: DialogArgs by navArgs()
        val binding = DialogBinding.bind(view)
       // val downloadManager: DownloadManager = getSystemService(DOWNLOAD_SERVICE) as DownloadManager

        binding.sendButton.setOnClickListener {
            val sendIntent: Intent = Intent().apply {
                action = Intent.ACTION_SEND
                putExtra(Intent.EXTRA_TEXT, args.imgUrl)
                type = "text/plain"
            }
            val shareIntent = Intent.createChooser(sendIntent, null)

            startActivity(shareIntent)
        }

    }
}