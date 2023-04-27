package com.example.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import android.app.AlertDialog
import android.content.DialogInterface
import android.graphics.Color

class Login : AppCompatActivity() {
    companion object {
        const val EXTRA_USERNAME = "com.example.login.EXTRA_USERNAME"
    }
    private lateinit var usernameEditText: EditText
    private lateinit var passwordEditText: EditText
    private lateinit var loginButton: Button
    private var errorCount = 3 // 最多允许 3 次错误登录
    private lateinit var errorCountTextView: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        //跳转到注册页面
        val registerButton = findViewById<TextView>(R.id.noaccount)
        registerButton.setOnClickListener {
            val intent = Intent(this, Signin::class.java)
            startActivity(intent)
        }
        val registerButton2 = findViewById<TextView>(R.id.textView4)
        registerButton2.setOnClickListener {
            val intent = Intent(this, Signin::class.java)
            startActivity(intent)
        }

        errorCountTextView = findViewById(R.id.textView5)
        usernameEditText = findViewById(R.id.editTextTextPersonName)
        passwordEditText = findViewById(R.id.editpassword)
        loginButton = findViewById(R.id.loginbutton)

        loginButton.setOnClickListener {
            val username = usernameEditText.text.toString()
            val password = passwordEditText.text.toString()

            //弹窗
            val builder = AlertDialog.Builder(this)
                .setTitle("Login")
                .setPositiveButton("OK", null)
            val VV=isValidCredentials(username, password)
            if (VV) {
                builder.setOnDismissListener {
                    finish()
                }
                val intent = Intent(this, Welcome::class.java)
                intent.putExtra(EXTRA_USERNAME, username)//put some data in intent
                startActivity(intent)
            } else {

                if (username.isNotBlank() && password.isNotBlank()) {
                    errorCount--
                    errorCountTextView.text = "$errorCount"
                    if (errorCount == 0) {
                        loginButton.isEnabled = false
                        builder.setMessage("Login failed.")
                        builder.setOnDismissListener {
                            finish()
                        }
                    } else {
                        builder.setMessage("Wrong account or wrong password, no more times to try ,please try again later!")
                        if (errorCount == 1) {
                            errorCountTextView.setTextColor(Color.RED)
                            builder.setMessage("Wrong account or wrong password,you only have one time!")
                        }
                        if (errorCount == 2) {
                            errorCountTextView.setTextColor(Color.rgb(255,153,0))//两次橙色
                        }
                    }
                }else{
                    builder.setMessage("emplty blank!may be you should register")
                }
                //Toast.makeText(this, "Wrong account or Wrong password!", Toast.LENGTH_SHORT).show()
                // 登录失败，显示错误信息

            }
            builder.create().show()
        }


    }
    //define whether the group of username and password can log in
    private fun isValidCredentials(username: String, password: String): Boolean {
        val dbHelper = DBHelper(this)
        val db = dbHelper.readableDatabase

        val cursor = db.query(
            DBHelper.TABLE_NAME,
            arrayOf(DBHelper.COLUMN_ACCOUNT, DBHelper.COLUMN_PASSWORD),
            "${DBHelper.COLUMN_ACCOUNT} = ? AND ${DBHelper.COLUMN_PASSWORD} = ?",
            arrayOf(username, password),
            null,
            null,
            null
        )

        val isValid = cursor.count > 0

        cursor.close()
        db.close()

        return isValid
    }
}
