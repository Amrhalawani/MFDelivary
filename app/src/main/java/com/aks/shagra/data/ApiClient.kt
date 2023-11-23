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


    @GET("/api/news")
    fun getNews(
        @Query("category_id") category_id: String? = null,
    ): Observable<NewsRes>

    @GET("/api/news/{newsId}/")
    fun getNewsDetails(
        @Path("newsId") newsId: String
    ): Observable<NewsDetailsRes>


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


//gallery

    @GET("/api/libraries")
    fun getLibraries(
    ): Observable<GalleryRes>


    @GET("/api/libraries/{album_id}/albums")
    fun getLibrariesAlbums(
        @Path("album_id") album_id: String
    ): Observable<AlbumsRes>


    @GET("/api/libraries/{libraryId}/albums/{album_id}")
    fun gelAlbumsOfSpecificDate(
        @Path("album_id") album_id: String,
        @Path("libraryId") libraryId: String
    ): Observable<AlbumDetailsRes>


    @GET("/api/albums/{album_id}/files")
    fun getAlbumFiles(
        @Path("album_id") album_id: String
    ): Observable<AlbumDetailsFilesRes>


    @GET("/api/users/authenticated")
    fun getProfile(
    ): Observable<ProfileRes>

    @FormUrlEncoded
    @POST("/api/newborns")
    fun addNewBorn(
        @Field("name") name: String,
        @Field("birthdate") birthdate: String,
        @Field("father_name") father_name: String,
        @Field("mother_name") mother_name: String,
    ): Observable<DefaultRes>


    @GET("/api/newborns")
    fun getNewBornCases(
    ): Observable<NewBornCasesRes>


    @GET("/api/death-cases")
    fun getDeathCases(
    ): Observable<DeathCasesRes>


    //    @FormUrlEncoded
//    @POST("/api/users/authenticated")
//    fun updateProfile(
//        @Field("name") name: String,
//      //  @Field("avatar") avatar: String,
//        @Field("job") job: String,
//        @Field("twitter_link") twitter: String,
//        @Field("facebook_link") fb: String,
//        @Field("whatsapp_link") wa: String,
//        @Field("instagram_link") insta: String,
//   //     @Field("birthdate") birthdate: String,
//    ): Observable<ProfileRes>
    //
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

    @FormUrlEncoded
    @POST("/api/death-cases")
    fun addDeathCase(
        @Field("name") name: String,
        @Field("death_date") death_date: String,
        @Field("relation") relation: String,
        @Field("burial_location_latitude") burial_location_latitude: String,
        @Field("burial_location_longitude") burial_location_longitude: String,
        @Field("burial_location") burial_location: String,

        ): Observable<DefaultRes>

    @FormUrlEncoded
    @POST("/api/contact-us")
    fun sendMsg(
        @Field("name")   name: String,
        @Field("email")  email: String,
        @Field("phone")  phone: String,
        @Field("message") message: String,
    ): Observable<DefaultRes>

    @GET("/api/users/courses")
    fun getCertificates(
    ): Observable<CertificationsRes>

    @Multipart
    @POST("/api/users/courses")
    fun addCertifacte(
        @Part filetoUpload: MultipartBody.Part?
    ): Observable<DefaultRes>


    @DELETE("/api/users/courses/{id}")
    fun deleteCertifecate(
        @Path("id") certifcate_id: String
    ): Observable<DefaultRes>


    @GET("/api/family-positions")
    fun getFamilyCategory(
    ): Observable<FamilyPersonsRes>


    @GET("/api/family-positions/{id}")
    fun getFamilyPersonsOneCategory(
        @Path("id") id: String
    ): Observable<FamilyPersonsOneItemRes>


    @GET("/api/family-members")
    fun search(
        @Query("q") data: String? = null,
        @Query("page") page: Int
    ): Observable<SearchRes>


}