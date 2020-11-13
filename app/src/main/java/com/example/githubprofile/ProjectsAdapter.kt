package com.example.githubprofile


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import de.hdodenhof.circleimageview.CircleImageView


class ProjectsAdapter (val activity: FragmentActivity, val listProjects : ArrayList<Project>) : RecyclerView.Adapter<ProjectsAdapter.ProjetViewHolder>() {
    class ProjetViewHolder(v: View) : RecyclerView.ViewHolder(v) {
        var title = v.findViewById<TextView>(R.id.titleProject)
        var description = v.findViewById<TextView>(R.id.description)
        var lang = v.findViewById<TextView>(R.id.language)
        var size = v.findViewById<TextView>(R.id.size)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProjetViewHolder {
        return ProjetViewHolder(
            LayoutInflater.from(activity).inflate(R.layout.project_layout, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return listProjects.size
    }

    override fun onBindViewHolder(holder: ProjetViewHolder, position: Int) {
        val project = listProjects[position]
        holder.title.text = project.title
        if (project.description != "null"){
            holder.description.text = project.description
        }
        holder.lang.text = project.lang
        holder.size.text = project.size.toString()+" KB"
    }


}