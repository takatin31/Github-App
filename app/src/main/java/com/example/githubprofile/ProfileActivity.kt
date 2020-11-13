package com.example.githubprofile

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.JsonObjectRequest
import com.squareup.picasso.Picasso
import de.hdodenhof.circleimageview.CircleImageView

class ProfileActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        val username = intent.getStringExtra("username")
        getData(username)

        val returnBtn = findViewById<ImageView>(R.id.returnBtn)
        returnBtn.setOnClickListener {
            finish()
        }

        val projectsBtn = findViewById<Button>(R.id.reposBtn)
        projectsBtn.setOnClickListener {
            var intent = Intent(this, ProjectsActivity::class.java)
            intent.putExtra("username", username)
            startActivity(intent)
        }

        val followersBtn = findViewById<TextView>(R.id.followersLink)
        followersBtn.setOnClickListener{
            var intent = Intent(this, FollowersActivity::class.java)
            intent.putExtra("username", username)
            startActivity(intent)
        }

        val followingBtn = findViewById<TextView>(R.id.followingLink)
        followingBtn.setOnClickListener{
            var intent = Intent(this, FollowingActivity::class.java)
            intent.putExtra("username", username)
            startActivity(intent)
        }
    }

    fun getData(username : String){
        val urlData = "https://api.github.com/users/$username"

        // Request a string response from the provided URL.
        val jsonRequestData = JsonObjectRequest(
            Request.Method.GET, urlData, null,
            Response.Listener { response ->
                val avatar_url = response.getString("avatar_url")
                val name = response.getString("name")
                val company = response.getString("company")
                val location = response.getString("location")
                val bio = response.getString("bio")
                val followers = response.getString("followers")
                val following = response.getString("following")

                if (avatar_url != ""){
                    val userPhoto = findViewById<CircleImageView>(R.id.userPhoto)
                    Picasso.get().load(avatar_url).into(`userPhoto`)
                }

                findViewById<TextView>(R.id.name).text = name
                findViewById<TextView>(R.id.username).text = username
                findViewById<TextView>(R.id.company).text = company
                findViewById<TextView>(R.id.location).text = location
                findViewById<TextView>(R.id.bio).text = bio
                findViewById<TextView>(R.id.followers).text = followers
                findViewById<TextView>(R.id.following).text = following


            },
            Response.ErrorListener {
                Log.d("Error", "Request error")
                Toast.makeText(this,"A problem occured", Toast.LENGTH_LONG).show()
                finish()
            })

        RequestHandler.getInstance(this).addToRequestQueue(jsonRequestData)
    }
}