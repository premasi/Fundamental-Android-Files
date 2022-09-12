package rk.enkidu.githubusers

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.recyclerview.widget.RecyclerView

class ListUsersAdapter(val listUser: ArrayList<Users>) : RecyclerView.Adapter<ListUsersAdapter.ListViewAdapter>() {

    class ListViewAdapter(itemView: View) : RecyclerView.ViewHolder(itemView){
        var tvUsername : TextView = itemView.findViewById(R.id.tv_username)
        var tvName : TextView = itemView.findViewById(R.id.tv_name)
        var ivPhoto : ImageView = itemView.findViewById(R.id.iv_avatar)
        val btnDetail : Button = itemView.findViewById(R.id.btn_profile)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewAdapter {
        val view : View = LayoutInflater.from(parent.context).inflate(
            R.layout.list_users,
            parent,
            false
        )
        return ListViewAdapter(view)
    }

    override fun onBindViewHolder(holder: ListViewAdapter, position: Int) {
        val (username, name, photo, followers, following, company, location, repository) = listUser[position]
        holder.ivPhoto.setImageResource(photo)
        holder.tvName.text = name
        holder.tvUsername.text = username

        holder.btnDetail.setOnClickListener {
            onItemClickCallback.onItemClicked(listUser[holder.adapterPosition])
        }

    }

    override fun getItemCount() = listUser.size


    //supaya bisa mengembalikan nilai
    //objek dari interface OnITemClickCallBack
    private lateinit var onItemClickCallback: OnItemClickCallback

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    interface OnItemClickCallback {
        fun onItemClicked(data: Users)
    }

}


