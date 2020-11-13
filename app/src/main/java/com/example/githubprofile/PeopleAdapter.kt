package com.example.githubprofile


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import de.hdodenhof.circleimageview.CircleImageView


class PeopleAdapter (val activity: FragmentActivity, val listPeople : ArrayList<People>) : RecyclerView.Adapter<PeopleAdapter.PeopleViewHolder>() {
    class PeopleViewHolder(v: View) : RecyclerView.ViewHolder(v) {
        var name = v.findViewById<TextView>(R.id.userName)
        var imageView = v.findViewById<CircleImageView>(R.id.userPhoto)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PeopleViewHolder {
        return PeopleViewHolder(
            LayoutInflater.from(activity).inflate(R.layout.people_layout, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return listPeople.size
    }

    override fun onBindViewHolder(holder: PeopleViewHolder, position: Int) {
        val people = listPeople[position]
        holder.name.text = people.name
        if (people.avatar_url != ""){
            Picasso.get().load(people.avatar_url).into(holder.imageView)
        }

    }


}