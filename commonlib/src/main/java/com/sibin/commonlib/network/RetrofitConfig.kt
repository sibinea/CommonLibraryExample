package com.sibin.commonlib.network

import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.security.KeyManagementException
import java.security.NoSuchAlgorithmException
import java.util.concurrent.TimeUnit


data class RetrofitConfig(
    var baseUrl: String = "",
    val connectionTimeoutInSeconds: Long = 20,
    val readTimeoutInSeconds: Long = 20
) {
    private val retrofit: Retrofit? = null
    private var mGsonConverter: GsonConverterFactory? = null
    private var mClient: OkHttpClient? = null

    internal fun provideRetrofit(): Retrofit {

        return retrofit
            ?: Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(gsonConverter)
                .client(client)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
    }

    private val client: OkHttpClient
        @Throws(NoSuchAlgorithmException::class, KeyManagementException::class)
        get() {
            if (mClient == null) {
                val interceptor = HttpLoggingInterceptor()
                interceptor.level = HttpLoggingInterceptor.Level.BODY

                val httpBuilder = OkHttpClient.Builder()
                httpBuilder
                    .connectTimeout(connectionTimeoutInSeconds, TimeUnit.SECONDS)
                    .readTimeout(readTimeoutInSeconds, TimeUnit.SECONDS)
                    .addInterceptor(interceptor)  /// show all JSON in logCat
                mClient = httpBuilder.build()

            }
            return mClient!!
        }

    private val gsonConverter: GsonConverterFactory
        get() {
            if (mGsonConverter == null) {
                mGsonConverter = GsonConverterFactory
                    .create(
                        GsonBuilder()
                            .setLenient()
                            .disableHtmlEscaping()
                            .create()
                    )
            }
            return mGsonConverter!!
        }
}