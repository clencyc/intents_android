package com.example.intents_clency

import android.annotation.SuppressLint
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Button
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat

class MainActivity : AppCompatActivity() {


    private lateinit var btnSMS: Button
    private lateinit var btnEMAIL: Button
    private lateinit var btnCamera: Button
    private lateinit var btnShare: Button
    private lateinit var btnMPESA: Button
    private lateinit var btnCALL: Button
    private lateinit var btnWEB: Button


    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnSMS = findViewById(R.id.btnsms)
        btnEMAIL = findViewById(R.id.btnemail)
        btnCamera = findViewById(R.id.btncamera)
        btnShare = findViewById(R.id.btnshare)
        btnMPESA = findViewById(R.id.btnmpesa)
        btnCALL = findViewById(R.id.btncall)
        btnWEB = findViewById(R.id.btnwesite)


        btnSMS.setOnClickListener {
            val uri: Uri = Uri.parse("smsto:0792552491")
            val intent = Intent(Intent.ACTION_SENDTO, uri)
            intent.putExtra("sms_body", "Vipi boss siku mingi")
            startActivity(intent)
        }
        btnEMAIL.setOnClickListener {
            val emailIntent =
                Intent(Intent.ACTION_SENDTO, Uri.fromParts("mail to", "clency2023@gmail.com", null))
            intent.putExtra(Intent.EXTRA_SUBJECT, "JOB APPLICATION")
            intent.putExtra(Intent.EXTRA_TEXT, "Hello My name is Clency Christine")
            startActivity(Intent.createChooser(emailIntent, "Send email..."))
        }
        btnCamera.setOnClickListener {
            val takePicturePreview = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            startActivityForResult(takePicturePreview, 1)
        }
        btnMPESA.setOnClickListener {
            val simToolkitintent =
                applicationContext.packageManager.getLaunchIntentForPackage("com.android.stk")
            simToolkitintent?.let { startActivity(it) }
        }
        btnCALL.setOnClickListener {
            val intent = Intent(Intent.ACTION_CALL, Uri.parse("tel:" + "0792552491"));
            if (ContextCompat.checkSelfPermission(
                    this@MainActivity,
                    android.Manifest.permission.CALL_PHONE)
                != PackageManager.PERMISSION_GRANTED
            ) {
                ActivityCompat.requestPermissions(
                    this@MainActivity,
                    arrayOf<String>(android.Manifest.permission.CALL_PHONE),
                    1
                )
            } else {
                startActivity(intent);
            }
        }

        btnShare.setOnClickListener {
            val shareIntent = Intent(Intent.ACTION_SEND)
            shareIntent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
            shareIntent.type = "text/plain"
            shareIntent.putExtra(Intent.EXTRA_TEXT, "Hey, download this app!")
            startActivity(shareIntent)
        }

        btnWEB.setOnClickListener {
            val gotowebsite = Intent(this, WebsiteActivity::class.java)
            startActivity(gotowebsite)
        }
    }
}