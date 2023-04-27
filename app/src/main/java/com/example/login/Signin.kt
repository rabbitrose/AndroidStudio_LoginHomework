package com.example.login

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import android.content.ContentValues
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.widget.Toast
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

class Signin : AppCompatActivity() {
    private lateinit var dbHelper: DBHelper
    private lateinit var accountEditText: TextInputEditText
    private lateinit var passwordEditText: TextInputEditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signin)

        dbHelper = DBHelper(this)//build the database
        accountEditText = findViewById(R.id.account_edittext)
        passwordEditText = findViewById(R.id.password_edittext)

        val registerButton = findViewById<Button>(R.id.register_button)
        registerButton.setOnClickListener {
            val account = accountEditText.text.toString()
            val password = passwordEditText.text.toString()

            if (account.isNotBlank() && password.isNotBlank()) {
                val db = dbHelper.writableDatabase
                val values = ContentValues().apply {
                    put(DBHelper.COLUMN_ACCOUNT, account)
                    put(DBHelper.COLUMN_PASSWORD, password)
                }
                db.insert(DBHelper.TABLE_NAME, null, values)//insert data into the database
                db.close()
                Toast.makeText(this, "Registered successfully", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Please enter account and password,both account and password should not be empty", Toast.LENGTH_SHORT).show()
            }
        }
        //点击按钮跳转登录

        val jumpButton3 = findViewById<ImageView>(R.id.back_login2)
        jumpButton3.setOnClickListener {
            val intent = Intent(this, Login::class.java)
            startActivity(intent)
        }





        //返回按钮
//        val registerButton = findViewById<Button>(R.id.register_button)
//        registerButton.setOnClickListener {
//            val intent = Intent(this, Login::class.java)
//            startActivity(intent)
//        }

    }
}