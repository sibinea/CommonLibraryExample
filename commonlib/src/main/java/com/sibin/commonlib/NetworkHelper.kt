package com.sibin.commonlib

import com.google.gson.JsonElement
import com.sibin.commonlib.network.*
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import okhttp3.MediaType
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.asRequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import retrofit2.Retrofit
import java.io.File


object NetworkHelper {
    private var retrofitConfig = RetrofitConfig()

    fun retrofitConfigure(func: RetrofitConfig.() -> Unit) {
        func(retrofitConfig)
    }

    fun getRetrofit(): Retrofit {
        return retrofitConfig.provideRetrofit()
    }

    inline fun <reified R> getCall(
        url:  String, crossinline successCallback: (R) -> Unit, crossinline failure: () -> Unit
    ) {
        ApiClass.getApi(url).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { response -> handleSuccess(response, successCallback, failure) },
                { failure() })
    }

    inline fun <reified R, T> postCall(
        url: String, request: T, crossinline successCallback: (R) -> Unit,
        crossinline failure: () -> Unit
    ) {
        ApiClass.postApi(url, request as Any).subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { response -> handleSuccess(response, successCallback, failure) },
                { failure() })
    }


    inline fun <reified R, P> postMultiPartCall(
        url: String, part: Map<String, RequestBody>, crossinline successCallback: (R) -> Unit,
        crossinline failure: () -> Unit
    ) {
        ApiClass.postMultiPartApi(url, part).subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { response -> handleSuccess(response, successCallback, failure) },
                { failure() })
    }

    inline fun <reified R, T> patchCall(
        url: String, request: T, crossinline successCallback: (R) -> Unit,
        crossinline failure: () -> Unit
    ) {
        ApiClass.patchApi(url, request as Any).subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { response -> handleSuccess(response, successCallback, failure) },
                { failure() })
    }

    inline fun <reified R, T> putCall(
        url: String, request: T, crossinline successCallback: (R) -> Unit,
        crossinline failure: () -> Unit
    ) {
        ApiClass.putApi(url, request as Any).subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { response -> handleSuccess(response, successCallback, failure) },
                { failure() })
    }

    inline fun deleteCall(
        url: String, crossinline successCallback: () -> Unit, crossinline failure: () -> Unit
    ) {

        ApiClass.deleteApi(url).subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { successCallback() },
                { failure() })
    }

    inline fun <reified R> handleSuccess(
        response: JsonElement,
        successCallback: (R) -> Unit,
        failure: () -> Unit
    ) {
        val data = MapperClass().transform<R>(response)
        if (data != null) {
            successCallback(data)
        } else {
            failure()
        }
    }


    fun toRequestBody(
        value: String,
        contentType: MediaType = "text/plain".toMediaType()
    ): RequestBody {
        return value.toRequestBody(contentType)
    }

    fun toRequestBody(
        file: File,
        contentType: MediaType = "image/jpeg".toMediaType()
    ): RequestBody {
        return file.asRequestBody(contentType)

    }

}