package rk.enkidu.githubusers20.fragment

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import rk.enkidu.githubusers20.R
import rk.enkidu.githubusers20.response.FollowersResponseItem

class ListFollowersAdapter(private val listFollow: List<FollowersResponseItem>) : RecyclerView.Adapter<ListFollowersAdapter.ListViewHolder>() {

    class ListViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val ivAvatar = view.findViewById<ImageView>(R.id.iv_avatar_follow)
        val tvUsername = view.findViewById<TextView>(R.id.tv_username_follow)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_follow, parent, false)
        return ListViewHolder(view)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val (login, avatar) = listFollow[position]
        holder.tvUsername.text = login.toString()
        Glide.with(holder.itemView.context)
            .load(avatar) // URL Gambar
            .circleCrop() // Mengubah image menjadi lingkaran
            .into(holder.ivAvatar) // imageView mana yang akan diterapkan

    }

    override fun getItemCount(): Int = listFollow.size
}