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

    @GET("search/users")
    @Headers("Authorization: token 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")
    suspend fun findUserAfterAddToFavorite(
        @Query("q") query: String
    ) : SearchUsersResponse

}