package com.l.androidjetpack

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.l.androidjetpack.helper.UserHelper


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (!UserHelper(this).StatusSplash) {
            val home = Intent(this, Splash::class.java)
            UserHelper(this).StatusSplash = true
            startActivity(home)
            finish()
        } else {
            Handler().postDelayed({
                val home = Intent(this, ButtonNav::class.java)
                startActivity(home)
                finish()
            }, 2000)
        }
    }
}

