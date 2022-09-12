package rk.enkidu.githubusers20

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import rk.enkidu.githubusers20.response.ItemsItem

class ListUsersAdapter (private val listUsers : List<ItemsItem>) : RecyclerView.Adapter<ListUsersAdapter.ListViewHolder>() {
    private lateinit var onItemClickCallBack : OnItemClickCallBack

    fun setOnItemClicked(onItemClickCallBack: OnItemClickCallBack){
        this.onItemClickCallBack = onItemClickCallBack
    }

    interface OnItemClickCallBack {
        fun onClicked(data: ItemsItem)
    }

    class ListViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val ivAvatar = view.findViewById<ImageView>(R.id.iv_avatar)
        val tvUsername = view.findViewById<TextView>(R.id.tv_username)
        val btnProfile = view.findViewById<Button>(R.id.btn_profile)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_users, parent, false)
        return ListViewHolder(view)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val (login, avatar) = listUsers[position]
        holder.tvUsername.text = login.toString()
        Glide.with(holder.itemView.context)
            .load(avatar) // URL Gambar
            .circleCrop() // Mengubah image menjadi lingkaran
            .into(holder.ivAvatar) // imageView mana yang akan diterapkan

        holder.btnProfile.setOnClickListener {
            onItemClickCallBack.onClicked(listUsers[holder.adapterPosition])

        }
    }

    override fun getItemCount(): Int = listUsers.size


}