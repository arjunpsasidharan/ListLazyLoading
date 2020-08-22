package com.quastio.listlazyloading.restclient

import com.google.gson.GsonBuilder
import com.quastio.listlazyloading.model.ResponseModel
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query
import java.util.concurrent.TimeUnit

object RestClient {


    private val defaultHttpClient: OkHttpClient by lazy {
        OkHttpClient.Builder()
            .connectTimeout(20, TimeUnit.SECONDS)
            .readTimeout(10, TimeUnit.SECONDS)
            .writeTimeout(10, TimeUnit.SECONDS)
            .addInterceptor { chain ->
                val request = chain.request().newBuilder()
                    .addHeader("x-api-key", "jvmNAyPNr1JhiCeUlYmB2ae517p3Th0aGG6syqMb").build()
                chain.proceed(request)
            }
            .build()
    }

    private val retrofitBuilder: Retrofit.Builder by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(defaultHttpClient)
            .addConverterFactory(
                GsonConverterFactory.create(
                    GsonBuilder()
                        .setDateFormat("yyyy-MM-dd'T'HH:mm:ssZ")
                        .create()
                )
            )
    }

    val dataApiService: DataApiService by lazy {
        retrofitBuilder.build().create(DataApiService::class.java)
    }


    interface DataApiService {
        @GET(NOW_PLAYING)
        suspend fun getDataList(
            @Query("offset") page: Int,
            @Query("limit") limit: Int = 6
        ): List<ResponseModel>

    }

    private const val BASE_URL = "https://qgkpjarwfl.execute-api.us-east-1.amazonaws.com/dev/"
    private const val NOW_PLAYING = "getNormalVideoFiles"

}