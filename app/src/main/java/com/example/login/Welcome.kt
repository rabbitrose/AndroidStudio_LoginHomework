package com.example.login

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class Welcome : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_welcome)
        val username = intent.getStringExtra(Login.EXTRA_USERNAME)//get data from intent
        val usernameTextView = findViewById<TextView>(R.id.transition_username)
        usernameTextView.text = username

        //点击按钮跳转登录
        val jumpButton3 = findViewById<ImageView>(R.id.back_login)
        jumpButton3.setOnClickListener {
            println(2313123)
            val intent = Intent(this, Login::class.java)
            startActivity(intent)
        }
    }
}