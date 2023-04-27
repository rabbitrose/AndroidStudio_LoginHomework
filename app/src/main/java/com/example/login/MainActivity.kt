package com.example.login

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_intro)
        setContentView(R.layout.activity_main)
    }
}

class DBHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        const val DATABASE_NAME = "myapp.db"
        const val DATABASE_VERSION = 1

        const val TABLE_NAME = "users"
        const val COLUMN_ID = "_id"
        const val COLUMN_ACCOUNT = "account"
        const val COLUMN_PASSWORD = "password"
    }

    override fun onCreate(db: SQLiteDatabase?) {
        //create database
        val createTable =
            "CREATE TABLE $TABLE_NAME ($COLUMN_ID INTEGER PRIMARY KEY AUTOINCREMENT, $COLUMN_ACCOUNT TEXT, $COLUMN_PASSWORD TEXT);"
        db?.execSQL(createTable)
    }
    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        // update the database
    }
}
