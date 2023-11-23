package com.aks.shagra.data

import com.aks.shagra.BuildConfig
import com.aks.shagra.data.models.*
import io.reactivex.Observable
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.http.*

interface ApiClient {
    companion object {
        const val BASE_URL = BuildConfig.BASE_URL // production
    }


    @FormUrlEncoded
    @POST("/api/register")
    fun register(
        @Field("code") code: String,
        @Field("name") name: String,
        @Field("phone") phone: String,
        @Field("player_id") player_id: String,
        @Field("type") type: String,

        @Field("demo_account_name") if_demo_account_name: String?,
        @Header("USER") if_demo: String?,
    ): Observable<LoginRes>


    @FormUrlEncoded
    @POST("/api/users/otp/send")
    fun sendOTP(
        @Field("phone") phone: String,
        @Header("USER") if_demo: String?,
        ): Observable<DefaultRes>


    @FormUrlEncoded
    @DELETE("/api/users/delete")
    fun deleteAccount(): Observable<DefaultRes>


//    @FormUrlEncoded
//    @POST("/api/refresh")
//    fun refreshToken(
//        @Field("refresh_token") refresh_token: String,
//    ): Observable<LoginRes>


    @FormUrlEncoded
    @POST("/api/login")
    fun login(
        @Field("code") code: String,
        @Field("phone") phone: String,
        @Field("player_id") player_id: String,
        @Field("type") type: String,

        @Field("demo_account_name") if_demo_account_name: String?,
        @Header("USER") if_demo: String?,
    ): Observable<LoginRes>

    @GET("/api/settings")
    fun getAppSettings(
    ): Observable<AppSettingsRes>

    @Multipart
    @POST("/api/users/authenticated")
    fun updateProfile(
        @Part("name") name: RequestBody,
        @Part("job") job: RequestBody?,
        @Part("twitter_link") twitter: RequestBody?,
        @Part("facebook_link") fb: RequestBody?,
        @Part("whatsapp_link") wa: RequestBody?,
        @Part("instagram_link") insta: RequestBody?,@Part("about") about: RequestBody?,@Part("address") address: RequestBody?,@Part("mother_name") mother_name: RequestBody?,
        @Part avatar: MultipartBody.Part?,
    ): Observable<ProfileRes>



}