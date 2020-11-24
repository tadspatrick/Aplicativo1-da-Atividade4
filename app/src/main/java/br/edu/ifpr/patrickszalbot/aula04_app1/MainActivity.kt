package br.edu.ifpr.patrickszalbot.aula04_app1

import android.Manifest
import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.karumi.dexter.Dexter
import com.karumi.dexter.PermissionToken
import com.karumi.dexter.listener.PermissionDeniedResponse
import com.karumi.dexter.listener.PermissionGrantedResponse
import com.karumi.dexter.listener.PermissionRequest
import com.karumi.dexter.listener.single.PermissionListener
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    companion object {
        const val PICK_IMAGE = 11
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        btnShareTxt.setOnClickListener { sharetxt(txtShare.text.toString()) }

        btnShareImg.setOnClickListener { shareimgexecute() }

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == PICK_IMAGE) {
            val uri = data?.data
            imgShare.setImageURI(uri)
            val intent = Intent().apply {
                action = Intent.ACTION_SEND
                putExtra(Intent.EXTRA_STREAM, uri)
                type = "image/*"
            }
            val shareIntent = Intent.createChooser(intent, null)
            startActivity(shareIntent)
        }
        super.onActivityResult(requestCode, resultCode, data)
    }

    private fun sharetxt(text: String) {
        val intent = Intent().apply {
            action = Intent.ACTION_SEND
            putExtra(Intent.EXTRA_TEXT, text)
            type = "text/plain"
        }
        val shareIntent = Intent.createChooser(intent, null)
        startActivity(shareIntent)
    }

    private fun shareimgexecute() {
        val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI)
        startActivityForResult(Intent.createChooser(intent, "Escolha uma imagem"), PICK_IMAGE)
    }
}