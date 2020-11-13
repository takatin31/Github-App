package com.example.githubprofile

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonArrayRequest

class FollowingActivity : AppCompatActivity() {
    lateinit var adapter: PeopleAdapter
    lateinit var layoutManager : LinearLayoutManager
    val peopleList = arrayListOf<People>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_following)

        val recycler = findViewById<RecyclerView>(R.id.recyclerView)

        layoutManager = LinearLayoutManager(this)
        recycler.layoutManager = layoutManager

        adapter = PeopleAdapter(this, peopleList)
        recycler.adapter = adapter

        val username = intent.getStringExtra("username")


        getPeople(username)

        val returnBtn = findViewById<ImageView>(R.id.returnBtn)
        returnBtn.setOnClickListener {
            finish()
        }
    }

    fun getPeople(username : String){
        val urlData = "https://api.github.com/users/$username/following"
        // Request a string response from the provided URL.
        val jsonRequestData = JsonArrayRequest(
            Request.Method.GET, urlData, null,
            Response.Listener { response ->
                peopleList.clear()
                for (i in 0 until response.length()){
                    val item = response.getJSONObject(i)
                    val name = item.getString("login")
                    val urlPhoto = item.getString("avatar_url")

                    val people = People(name, urlPhoto)
                    peopleList.add(people)
                }
                adapter.notifyDataSetChanged()
            },
            Response.ErrorListener { Log.d("Error", "Request error") })

        RequestHandler.getInstance(this).addToRequestQueue(jsonRequestData)
    }
}