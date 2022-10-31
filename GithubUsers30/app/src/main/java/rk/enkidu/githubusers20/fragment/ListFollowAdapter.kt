package rk.enkidu.githubusers20.fragment

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import rk.enkidu.githubusers20.databinding.ListFollowBinding
import rk.enkidu.githubusers20.response.FollowersResponseItem

class ListFollowersAdapter(private val listFollow: List<FollowersResponseItem>) : RecyclerView.Adapter<ListFollowersAdapter.ListViewHolder>() {

    class ListViewHolder(private val itemBinding: ListFollowBinding) : RecyclerView.ViewHolder(itemBinding.root) {
        fun bind(itemItem: FollowersResponseItem) {
            itemBinding.tvUsernameFollow.text = itemItem.login
            Glide.with(itemView.context)
                .load(itemItem.avatarUrl) // URL Gambar
                .circleCrop() // Mengubah image menjadi lingkaran
                .into(itemBinding.ivAvatarFollow) // imageView mana yang akan diterapkan
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val itemBinding = ListFollowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ListViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val itemItem: FollowersResponseItem = listFollow[position]
        holder.bind(itemItem)

    }

    override fun getItemCount(): Int = listFollow.size
}