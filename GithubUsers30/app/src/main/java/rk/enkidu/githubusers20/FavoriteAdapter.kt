package rk.enkidu.githubusers20

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import rk.enkidu.githubusers20.data.entity.UserEntity
import rk.enkidu.githubusers20.databinding.ListUsersBinding
import rk.enkidu.githubusers20.response.ItemsItem

class FavoriteAdapter : RecyclerView.Adapter<FavoriteAdapter.FavoriteViewHolder>() {

    private val listFav = ArrayList<UserEntity>()

    fun setListFav(user: List<UserEntity>){
        listFav.clear()
        listFav.addAll(user)
        notifyDataSetChanged()
    }

    class FavoriteViewHolder(private val binding: ListUsersBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(userEntity: UserEntity){

            val item = ItemsItem(
                userEntity.username,
                userEntity.avatarUrl,
                userEntity.id
            )

            binding.apply {
                tvUsername.text = userEntity.username

                Glide.with(itemView.context)
                    .load(userEntity.avatarUrl)
                    .into(ivAvatar)

                btnProfile.setOnClickListener {
                    val intent = Intent(it.context, DetailActivity::class.java)
                    intent.putExtra(DetailActivity.LOGIN, item)
                    itemView.context.startActivity(intent)
                }
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteViewHolder {
        val binding = ListUsersBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return FavoriteViewHolder(binding)
    }

    override fun onBindViewHolder(holder: FavoriteViewHolder, position: Int) {
        holder.bind(listFav[position])
    }

    override fun getItemCount() : Int = listFav.size
}