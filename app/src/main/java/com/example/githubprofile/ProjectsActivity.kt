package com.example.githubprofile

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.JsonObjectRequest
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class ProjectsActivity : AppCompatActivity() {

    lateinit var adapter: ProjectsAdapter
    lateinit var layoutManager : LinearLayoutManager
    val projectList = arrayListOf<Project>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_projects)

        val recycler = findViewById<RecyclerView>(R.id.recyclerView)

        layoutManager = LinearLayoutManager(this)
        recycler.layoutManager = layoutManager

        adapter = ProjectsAdapter(this, projectList)
        recycler.adapter = adapter

        val username = intent.getStringExtra("username")


        getProjects(username)

        val returnBtn = findViewById<ImageView>(R.id.returnBtn)
        returnBtn.setOnClickListener {
            finish()
        }
    }

    fun getProjects(username : String){
        val urlData = "https://api.github.com/users/$username/repos"
        // Request a string response from the provided URL.
        val jsonRequestData = JsonArrayRequest(
            Request.Method.GET, urlData, null,
            Response.Listener { response ->
                projectList.clear()
                for (i in 0 until response.length()){
                    val item = response.getJSONObject(i)
                    val projectId = item.getInt("id")
                    val projectTitle = item.getString("name")
                    var projectDescitption = item.getString("description")
                    var projectLang = item.getString("language")
                    val projectSize = item.getInt("size")
                    val project = Project(projectId, projectTitle, projectDescitption, projectLang, projectSize)
                    projectList.add(project)
                }
                adapter.notifyDataSetChanged()
            },
            Response.ErrorListener { Log.d("Error", "Request error") })

        RequestHandler.getInstance(this).addToRequestQueue(jsonRequestData)
    }
}