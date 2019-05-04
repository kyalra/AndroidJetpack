package com.l.androidjetpack.upload

import android.Manifest
import android.app.Activity
import android.app.ProgressDialog
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import com.l.androidjetpack.R
import kotlinx.android.synthetic.main.up_1.*
import java.io.IOException
import java.util.*

class FullCost : AppCompatActivity() {

    val PICK_IMAGE_REQUEST = 71
    val PERMISSION_REQUEST_CODE = 1001
    var value = 0.0
    lateinit var filepath: Uri
    lateinit var storage: FirebaseStorage
    lateinit var storageReference: StorageReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.up_1)
        storage = FirebaseStorage.getInstance()
        storageReference = storage.reference

        up_1.setOnClickListener {
            when {
                (Build.VERSION.SDK_INT >= Build
                    .VERSION_CODES.M) -> {
                    if (ContextCompat.checkSelfPermission(
                            this@FullCost,
                            Manifest.permission
                                .READ_EXTERNAL_STORAGE
                        )
                        != PackageManager.PERMISSION_GRANTED
                    ) {
                        requestPermissions(arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE), PERMISSION_REQUEST_CODE)
                    } else {
                        chooseImage()
                    }
                }
                else -> chooseImage()
            }
        }
        btnPost1.setOnClickListener {
            uploadImage()
        }
    }

    private fun uploadImage() {
        val progres = ProgressDialog(this).apply {
            setTitle("Uploading Picture...")
            setCancelable(false)
            setCanceledOnTouchOutside(false)
            show()
        }
        var ref: StorageReference = storageReference.child("images/" + UUID.randomUUID().toString())
        ref.putFile(filepath).addOnSuccessListener { taskSnapshot ->
            progres.dismiss()
            Toast.makeText(this@FullCost, "Uploaded", Toast.LENGTH_SHORT).show();
        }
            .addOnFailureListener { e ->
                progres.dismiss()
                Toast.makeText(this@FullCost, "Failed" + e.message, Toast.LENGTH_SHORT).show();
            }.addOnProgressListener { taskSnapshot ->
                value = (100.0 * taskSnapshot
                    .getBytesTransferred() / taskSnapshot
                    .getTotalByteCount())
                progres.setMessage("Uploaded..." + value.toInt())
            }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == Activity.RESULT_OK && data != null && data.data != null) {
            filepath = data.data
            try {
                var bitmap: Bitmap = MediaStore.Images.Media.getBitmap(contentResolver, filepath)
                imgView.setImageBitmap(bitmap)
            } catch (e: IOException) {
                e.printStackTrace()
            }
        }
    }

    private fun chooseImage() {
        val intent = Intent().apply {
            type = "image/*"
            action = Intent.ACTION_GET_CONTENT
        }
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST)
    }
}
