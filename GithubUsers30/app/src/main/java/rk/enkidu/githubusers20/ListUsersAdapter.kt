package rk.enkidu.githubusers20

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import rk.enkidu.githubusers20.databinding.ListUsersBinding
import rk.enkidu.githubusers20.response.ItemsItem

class ListUsersAdapter (private val listUsers : List<ItemsItem>) : RecyclerView.Adapter<ListUsersAdapter.ListViewHolder>() {

    class ListViewHolder(private val itemBinding: ListUsersBinding) : RecyclerView.ViewHolder(itemBinding.root) {
        fun bind(itemItem: ItemsItem) {
            itemBinding.tvUsername.text = itemItem.login

            Glide.with(itemView.context)
                .load(itemItem.avatarUrl) // URL Gambar
                .circleCrop() // Mengubah image menjadi lingkaran
                .into(itemBinding.ivAvatar) // imageView mana yang akan diterapkan

            itemBinding.btnProfile.setOnClickListener {
                val intent = Intent(it.context, DetailActivity::class.java)
                intent.putExtra(DetailActivity.LOGIN, itemItem)
                it.context.startActivity(intent)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val itemBinding = ListUsersBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ListViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val itemItem: ItemsItem = listUsers[position]
        holder.bind(itemItem)
    }

    override fun getItemCount(): Int = listUsers.size


}