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


}


