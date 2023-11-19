package com.aks.shagra.di

import android.content.Context
import android.content.SharedPreferences
import android.net.ConnectivityManager
import android.os.Build
import android.util.Log
import com.aks.shagra.BuildConfig
import com.aks.shagra.data.ApiClient
import com.aks.shagra.data.local.SharedPref
import com.chuckerteam.chucker.api.ChuckerInterceptor
import com.google.gson.Gson
import com.google.gson.GsonBuilder
//import androidx.room.Room
//import androidx.security.crypto.EncryptedSharedPreferences
//import androidx.security.crypto.MasterKey
//import com.chuckerteam.chucker.api.ChuckerInterceptor
//import com.google.gson.Gson
//import com.google.gson.GsonBuilder
//import com.gt.playitleague.BuildConfig
//import com.gt.playitleague.data.local.AppDatabase
//import com.gt.playitleague.data.local.SharedPreferencesManager
//import com.gt.playitleague.data.remote.ApiClient
//
//import com.gt.playitleague.data.remote.MainRepository
//import com.gt.playitleague.data.services.AppFirebaseMessagingService
//import com.gt.playitleague.data.utils.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
//import okhttp3.*
//import okhttp3.logging.HttpLoggingInterceptor
//import retrofit2.Retrofit
//import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
//import retrofit2.converter.gson.GsonConverterFactory
import java.io.File
import java.util.concurrent.TimeUnit
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class ApplicationModule {

//    val HEADER_CACHE_CONTROL = "Cache-Control"
//    val HEADER_PRAGMA = "Pragma"
//    private val cacheSize = (5 * 1024 * 1024).toLong()
//
    @Provides
    @Singleton
    fun providesGsonBuilder(): GsonBuilder = GsonBuilder().setLenient()

    @Provides
    @Singleton
    fun providesJson(gsonBuilder:GsonBuilder): Gson = gsonBuilder.create()

    @Provides
    @Singleton
    fun providesOkHttpClient(
        @ApplicationContext application: Context,
  //      sharedPref: SharedPreferencesManager
//        ,
//        dbClient: AppDatabase
    ): OkHttpClient =
        if (BuildConfig.DEBUG) {
            val loggingInterceptor = HttpLoggingInterceptor()
            loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
            OkHttpClient.Builder()
                .readTimeout(30, TimeUnit.SECONDS)
                .writeTimeout(30, TimeUnit.SECONDS)
                .connectTimeout(30, TimeUnit.SECONDS)
                .addInterceptor(loggingInterceptor)
        //       .addInterceptor(ChuckerInterceptor(application))
               .addInterceptor(NetworkInterceptor(/*sharedPref, dbClient*/))
//                .addNetworkInterceptor(networkInterceptor())
//                .addInterceptor(offlineInterceptor(application))
 //               .cache(cache(application))
                .build()
        } else {
            OkHttpClient.Builder()
                .readTimeout(30, TimeUnit.SECONDS)
                .writeTimeout(30, TimeUnit.SECONDS)
                .connectTimeout(30, TimeUnit.SECONDS)
   //             .addInterceptor(ChuckerInterceptor(application))
  //              .addInterceptor(NetworkInterceptor(sharedPref, dbClient))
//                .addNetworkInterceptor(networkInterceptor())
//                .addInterceptor(offlineInterceptor(application))
//                .cache(cache(application))
                .build()
        }
//
//
    @Provides
    @Singleton
    fun providesRetrofit(okHttpClient: OkHttpClient, gson: Gson): Retrofit {
        return Retrofit.Builder()
            .baseUrl(ApiClient.BASE_URL)
            .client(okHttpClient)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create(gson)).build()
    }

    @Provides
    @Singleton
    fun providesApiClient(retrofit: Retrofit): ApiClient {
        return retrofit.create(ApiClient::class.java)
    }
//
    private class NetworkInterceptor(  /*val sharedPref: SharedPreferencesManager,*/ ) : Interceptor {
        override fun intercept(chain: Interceptor.Chain): Response {
            try {
                var token = SharedPref.getAccessToken()
                    val builder: Request.Builder = chain.request()?.newBuilder()
//            var url = chain.request().url.toString().replace("/:",":")
//            builder.url(URL(url))

                if(chain?.request()?.headers["Authorization"]== null){
                    builder?.header("Authorization", "Bearer $token")
                }

                if (chain?.request()?.headers["USER"]== null) {
                   builder.header("USER",  SharedPref. getUserDemoAccount()!!)
                }


                builder?.header("Content-Type", "application/json")
                val request: Request = builder?.build()
                val response:Response =  chain?.proceed(request)
                try {

                    if (BuildConfig.DEBUG) {
                       Log.d("NetworkInterceptor ", " Request : ${request}")
                       response?.body?.let { Log.d("NetworkInterceptor ", " Response: ${it}") }
                   }
                } catch (e: Exception) {}
                return response
            } catch (e: Exception) {
                Log.e("TAG", "intercept: ${e.message}")
                val response:Response =  chain?.proceed(chain?.request())
                return response
//                return throw Exception(response.toString())
            }
        }

    }
//
//    // Caching requests ...
//    // This interceptor will be called ONLY if the network is available
//    private fun networkInterceptor(): Interceptor {
//        return Interceptor { chain ->
//
//            val response = chain.proceed(chain.request())
//            val cacheControl: CacheControl = CacheControl.Builder()
//                .maxAge(5, TimeUnit.SECONDS)
//                .build()
//            response.newBuilder()
//                .removeHeader(HEADER_PRAGMA)
//                .removeHeader(HEADER_CACHE_CONTROL)
//                .header(HEADER_CACHE_CONTROL, cacheControl.toString())
//                .build()
//        }
//    }
//    private fun offlineInterceptor(application: Context): Interceptor {
//        return Interceptor { chain ->
//
//            var request = chain.request()
//
//            // prevent caching when network is on. For that we use the "networkInterceptor"
//            if (!isNetWorkAvailable(application)) {
//                val cacheControl: CacheControl = CacheControl.Builder()
//                    .maxStale(7, TimeUnit.DAYS)
//                    .build()
//                request = request.newBuilder()
//                    .removeHeader(HEADER_PRAGMA)
//                    .removeHeader(HEADER_CACHE_CONTROL)
//                    .cacheControl(cacheControl)
//                    .build()
//            }
//            chain.proceed(request)
//
//        }
//
//
//    }
//
//
//    @Provides
//    @Singleton
//    fun providesSharedPreferenceManager(@ApplicationContext application: Context): SharedPreferencesManager {
//        SharedPreferencesManager(application).initialize(application)
//        return SharedPreferencesManager(application)
//    }
//
//
//    /*@Provides
//    @Singleton
//    fun providesSharedPreference(@ApplicationContext application: Context): SharedPreferences {
//        var sharedPreferences: SharedPreferences
//
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
//            sharedPreferences = EncryptedSharedPreferences.create(
//                    application,
//                    Constants.SHARED_PREFERENCES_NAME,
//                    getMasterKey(application),
//                    EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
//                    EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
//            )
//        } else {
//            sharedPreferences =
//                application.getSharedPreferences(
//                    Constants.SHARED_PREFERENCES_NAME,
//                    Context.MODE_PRIVATE
//                )
//        }
//        return sharedPreferences
//    }
//
//    private fun getMasterKey(application: Context): MasterKey {
//        return MasterKey.Builder(application)
//            .setKeyScheme(MasterKey.KeyScheme.AES256_GCM)
//            .build()
//    }
//
//    @Provides
//    fun providesSharedPreferenceEditor(sharedPreferences: SharedPreferences): SharedPreferences.Editor {
//        return sharedPreferences.edit()
//    }*/
//
//
//
//    @Provides
//    @Singleton
//    fun providesFireBaseMessagingService(
//        mainRepository: MainRepository/*,
//        mSharedPreferences: SharedPreferencesManager*/
//    ): AppFirebaseMessagingService {
//        return AppFirebaseMessagingService(/*appRepository, mSharedPreferences*/)
//    }
//
//
//    @Provides
//    @Singleton
//    fun provideRoomDB(@ApplicationContext application: Context): AppDatabase =
//        Room.databaseBuilder(
//            application.applicationContext,
//            AppDatabase::class.java,
//            Constants.DATA_BASE_NAME
//        ).allowMainThreadQueries()
//            .fallbackToDestructiveMigration().build()
//
//    @Provides
//    @Singleton
//    fun provideDao(appDb: AppDatabase) = appDb.userDao()
//
//
//    private fun cache(application: Context): Cache? {
//        return Cache(File(application.cacheDir, "PIL"), cacheSize)
//    }
//
//    private fun isNetWorkAvailable(application: Context): Boolean {
//        (application.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager).activeNetworkInfo.also {
//            return it != null && it.isConnected && it.isConnectedOrConnecting
//        }
//    }
}