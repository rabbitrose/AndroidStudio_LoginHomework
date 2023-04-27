package com.example.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView


class Intro : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_intro)


        //点击按钮跳转登录
        val jumpButton = findViewById<ImageView>(R.id.jump)
        jumpButton.setOnClickListener {
            println(2313123)
            val intent = Intent(this, Login::class.java)
            startActivity(intent)
        }
    }

}