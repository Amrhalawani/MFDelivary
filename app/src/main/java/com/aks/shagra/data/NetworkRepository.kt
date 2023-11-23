package com.aks.shagra.data


import com.aks.shagra.data.models.*
import io.reactivex.Observable
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.asRequestBody
import java.io.File
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NetworkRepository @Inject constructor(
    private var apiClient: ApiClient
) {
    fun getNews(category_id: String?): Observable<NewsRes> {
        return apiClient.getNews(category_id)
    }


    fun getNewsDetails(newsId: String): Observable<NewsDetailsRes> {
        return apiClient.getNewsDetails(newsId)
    }


    fun register(
        code: String,
        name: String,
        phone: String,
        player_id: String,
        if_demo_account_name: String?
    ): Observable<LoginRes> {
        return apiClient.register(
            code,
            name,
            phone,
            player_id,
            "ANDROID",
            if_demo_account_name,
            if_demo_account_name

        )
    }


    fun sendOTP(phone: String, if_demo: String): Observable<DefaultRes> {
        return apiClient.sendOTP(phone, if_demo)
    }

    fun deleteAccount(): Observable<DefaultRes> {
        return apiClient.deleteAccount()
    }


//    fun refreshToken(refresh_token: String): Observable<LoginRes> {
//        return apiClient.refreshToken(refresh_token)
//    }


    fun login(
        code: String,
        phone: String,
        player_id: String,
        if_demo_account_name: String?,
    ): Observable<LoginRes> {
        return apiClient.login(
            code, phone, player_id, "ANDROID", if_demo_account_name,
            if_demo_account_name,
        )
    }


    fun getAppSettings(): Observable<AppSettingsRes> {
        return apiClient.getAppSettings()
    }


//gallery

    fun getLibraries(): Observable<GalleryRes> {
        return apiClient.getLibraries()
    }


    fun getLibrariesAlbums(album_id: String): Observable<AlbumsRes> {
        return apiClient.getLibrariesAlbums(album_id)
    }

    fun gelAlbumsOfSpecificDate(
        date_id: String,
        libraryId: String
    ): Observable<AlbumDetailsRes> {
        return apiClient.gelAlbumsOfSpecificDate(date_id, libraryId)
    }


    fun getAlbumFiles(album_id: String): Observable<AlbumDetailsFilesRes> {
        return apiClient.getAlbumFiles(album_id)
    }


    private fun createMultiPartFromImagePath(
        imagePath: String?,
        varName: String
    ): MultipartBody.Part? {
        var imageMultipartBody: MultipartBody.Part? = null
        if (imagePath != null && imagePath.isNotEmpty()) {
            val file1 = File(imagePath)
            //  val compressedFile = Compressor(AdviceApp.appContext).compressToFile(file1);
            val list = imagePath.split(".")
            val extensionType = list.get(list.size - 1)

            val requestBody1 =
                file1.asRequestBody("image/${extensionType}".toMediaTypeOrNull())
            val rand1 = (0..1000).random() //random values for // filename



            imageMultipartBody =
                MultipartBody.Part.createFormData(
                    varName,
                    "${rand1.toString()}.${extensionType}",
                    requestBody1
                )
        } else {
            imageMultipartBody = null
        }

        return imageMultipartBody
    }


    fun getProfile(): Observable<ProfileRes> {
        return apiClient.getProfile()
    }


    fun addNewBorn(
        name: String,
        birthdate: String,
        father_name: String,
        mother_name: String,
    ): Observable<DefaultRes> {
        return apiClient.addNewBorn(name, birthdate, father_name, mother_name)
    }


    fun getNewBornCases(
    ): Observable<NewBornCasesRes> {
        return apiClient.getNewBornCases()
    }


    fun getDeathCases(
    ): Observable<DeathCasesRes> {
        return apiClient.getDeathCases()
    }


//    @POST("/api/users/authenticated")
//    fun updateProfile(
//        @Field("name") name: String,
//        // @Field("avatar") avatar: String,
//        @Field("job") job: String,
//        @Field("twitter_link") twitter: String,
//        @Field("facebook_link") fb: String,
//        @Field("whatsapp_link") wa: String,
//        @Field("instagram_link") insta: String,
//        @Field("birthdate") birthdate: String,
//    ): Observable<ProfileRes>


    fun updateProfile(
        name: String,
        job: String,
        twitter: String,
        fb: String,
        wa: String,
        insta: String,
        avatarPath: String?,
        aboutme: String,
        address: String,
        mother_name: String
    ): Observable<ProfileRes> {
        val textType = "text/plain"

        val nameRB: RequestBody = RequestBody.create(textType.toMediaTypeOrNull(), name)
        val jobRB: RequestBody = RequestBody.create(textType.toMediaTypeOrNull(), job)
        val twitterRB: RequestBody = RequestBody.create(textType.toMediaTypeOrNull(), twitter)
        val fbRB: RequestBody = RequestBody.create(textType.toMediaTypeOrNull(), fb)
        val waRB: RequestBody = RequestBody.create(textType.toMediaTypeOrNull(), wa)
        val instaRB: RequestBody = RequestBody.create(textType.toMediaTypeOrNull(), insta)
        val aboutmeRB: RequestBody = RequestBody.create(textType.toMediaTypeOrNull(), aboutme)
        val addressRB: RequestBody = RequestBody.create(textType.toMediaTypeOrNull(), address)
        val mother_nameRB: RequestBody =
            RequestBody.create(textType.toMediaTypeOrNull(), mother_name)

        val cardImageMultipartBody: MultipartBody.Part? =
            createMultiPartFromImagePath(avatarPath, "avatar")

        return apiClient.updateProfile(
            nameRB,
            jobRB,
            twitterRB,
            fbRB,
            waRB,
            instaRB,
            aboutmeRB,
            addressRB,
            mother_nameRB,
            cardImageMultipartBody
        )
    }

    fun addDeathCase(
        name: String,
        death_date: String,
        relation: String,
        lat: String,
        long: String,
        address: String
    ): Observable<DefaultRes> {
        return apiClient.addDeathCase(name, death_date, relation, lat, long, address)
    }


    fun sendMsg(
        name: String,
        email: String,
        phone: String,
        message: String,
    ): Observable<DefaultRes> {
        return apiClient.sendMsg(name, email, phone, message)
    }

    fun getCertificates(): Observable<CertificationsRes> {
        return apiClient.getCertificates()
    }


    fun uploadCertificateImage(
        imagePath: String
    ): Observable<DefaultRes> {
        val cardImageMultipartBody: MultipartBody.Part? =
            createMultiPartFromImagePath(imagePath, "image")
        return apiClient.addCertifacte(cardImageMultipartBody)

    }


    fun deleteCertifecate(
        id: String,
    ): Observable<DefaultRes> {
        return apiClient.deleteCertifecate(id)
    }


    fun getFamilyCategory(
    ): Observable<FamilyPersonsRes> {
        return apiClient.getFamilyCategory()
    }


    fun getFamilyPersonsOneCategory(id: String): Observable<FamilyPersonsOneItemRes> {
        return apiClient.getFamilyPersonsOneCategory(id)
    }

    fun search(data: String, pageNum: Int): Observable<SearchRes> {
        return apiClient.search(data, pageNum)
    }

}


