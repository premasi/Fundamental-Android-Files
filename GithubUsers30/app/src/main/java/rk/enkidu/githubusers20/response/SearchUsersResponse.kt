package rk.enkidu.githubusers20.response

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

data class SearchUsersResponse(

	@field:SerializedName("total_count")
	val totalCount: Int? = null,

	@field:SerializedName("incomplete_results")
	val incompleteResults: Boolean? = null,

	@field:SerializedName("items")
	val items: List<ItemsItem>
)

@Parcelize
data class ItemsItem(

	@field:SerializedName("login")
	val login: String? = null,


	@field:SerializedName("avatar_url")
	val avatarUrl: String? = null,


	@field:SerializedName("id")
	val id: Int? = null

) : Parcelable
