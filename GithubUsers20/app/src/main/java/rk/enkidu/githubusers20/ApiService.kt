package rk.enkidu.githubusers20

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query
import rk.enkidu.githubusers20.response.DetailResponse
import rk.enkidu.githubusers20.response.FollowersResponseItem
import rk.enkidu.githubusers20.response.FollowingResponseItem
import rk.enkidu.githubusers20.response.SearchUsersResponse


interface ApiService {
    @GET("search/users")
    @Headers("Authorization: token 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")
    fun findUserBySearch(
        @Query("q") query: String
    ) : Call<SearchUsersResponse>

    @GET("users/{username}")
    @Headers("Authorization: token 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")
    fun detailUser(
        @Path("username") query: String
    ) : Call<DetailResponse>

    @GET("users/{username}/followers")
    @Headers("Authorization: token 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")
    fun detailUserFollowers(
        @Path("username") query: String
    ) : Call<List<FollowersResponseItem>>

    @GET("users/{username}/following")
    @Headers("Authorization: token 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")
    fun detailUserFollowing(
        @Path("username") query: String
    ) : Call<List<FollowingResponseItem>>
}