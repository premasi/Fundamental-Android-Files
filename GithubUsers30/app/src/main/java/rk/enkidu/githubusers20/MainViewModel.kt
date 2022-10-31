package rk.enkidu.githubusers20

import android.app.Application
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import rk.enkidu.githubusers20.data.UserRepository
import rk.enkidu.githubusers20.data.entity.UserEntity
import rk.enkidu.githubusers20.response.*

class MainViewModel(application: Application) : ViewModel() {
    private val _item = MutableLiveData<List<ItemsItem>>()
    val item : LiveData<List<ItemsItem>> = _item

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading : LiveData<Boolean> = _isLoading

    private val _users = MutableLiveData<TempDetailResponse>()
    val users : LiveData<TempDetailResponse> = _users

    private val _followers = MutableLiveData<List<FollowersResponseItem>>()
    val followers : LiveData<List<FollowersResponseItem>> = _followers

    private val _following = MutableLiveData<List<FollowingResponseItem>>()
    val following : LiveData<List<FollowingResponseItem>> = _following

    private val userRepository : UserRepository = UserRepository(application)

    //get favorite users
    fun getAllUser() : LiveData<List<UserEntity>> = userRepository.getSaved()

    // search user in Main Activity
    fun searchUser(query: String){
        _isLoading.value = true
        val client = ApiConfig.getApiService().findUserBySearch(query)

        client.enqueue(object : Callback<SearchUsersResponse>{
            override fun onResponse(
                call: Call<SearchUsersResponse>,
                response: Response<SearchUsersResponse>
            ) {
                if(response.isSuccessful){
                    _isLoading.value = false
                    val responseBody = response.body()
                    if(responseBody != null){
                        _item.value = response.body()!!.items
                    }
                }

            }

            override fun onFailure(call: Call<SearchUsersResponse>, t: Throwable) {
                _isLoading.value = false
                Log.e(TAG, "onFailure: ${t.message}")
            }

        })
    }

    //fun specific data user in DetailActivity
    fun getDetailUser(query: String){
        _isLoading.value = true
        val client = ApiConfig.getApiService().detailUser(query)
        Log.v("query", query)

        client.enqueue(object : Callback<DetailResponse>{
            override fun onResponse(
                call: Call<DetailResponse>,
                response: Response<DetailResponse>
            ) {
                if(response.isSuccessful){
                    _isLoading.value = false
                    val responseBody = response.body()
                    if(responseBody != null){

                        val temp = TempDetailResponse(
                            response.body()?.login,
                            response.body()?.name,
                            response.body()?.avatarUrl,
                            response.body()?.followers,
                            response.body()?.following,
                            response.body()?.company,
                            response.body()?.location,
                            response.body()?.bio
                        )

                        _users.value = temp


                    }
                } else {
                    Log.v("Failure", response.message())
                }

            }

            override fun onFailure(call: Call<DetailResponse>, t: Throwable) {
                _isLoading.value = false
                Log.e(TAG, "onFailure: ${t.message}")
            }

        })
    }

    //get follower list
    fun getFollowers(query: String){
        _isLoading.value = true
        val client = ApiConfig.getApiService().detailUserFollowers(query)

        client.enqueue(object: Callback<List<FollowersResponseItem>>{
            override fun onResponse(
                call: Call<List<FollowersResponseItem>>,
                response: Response<List<FollowersResponseItem>>
            ) {
                if(response.isSuccessful){
                    _isLoading.value = false
                    val responseBody = response.body()
                    if(responseBody != null){
                        _followers.value = response.body()
                    }
                }
            }

            override fun onFailure(call: Call<List<FollowersResponseItem>>, t: Throwable) {
                _isLoading.value = false
                Log.e(TAG, "onFailure: ${t.message}")
            }
        })

    }

    //get following list
    fun getFollowing(query: String){
        _isLoading.value = true
        val client = ApiConfig.getApiService().detailUserFollowing(query)

        client.enqueue(object: Callback<List<FollowingResponseItem>>{
            override fun onResponse(
                call: Call<List<FollowingResponseItem>>,
                response: Response<List<FollowingResponseItem>>
            ) {
                if(response.isSuccessful){
                    _isLoading.value = false
                    val responseBody = response.body()
                    if(responseBody != null){
                        _following.value = response.body()
                    }
                }
            }

            override fun onFailure(call: Call<List<FollowingResponseItem>>, t: Throwable) {
                _isLoading.value = false
                Log.e(TAG, "onFailure: ${t.message}")
            }
        })

    }

    companion object{
        const val TAG = "MainViewModel"

    }
}