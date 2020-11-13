package com.example.githubprofile

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val searchBtn = findViewById<Button>(R.id.searchBtn)

        searchBtn.setOnClickListener {
            val username = findViewById<TextView>(R.id.username).text.toString()
            var intent = Intent(this, ProfileActivity::class.java)
            intent.putExtra("username", username)
            startActivity(intent)
        }
    }
}