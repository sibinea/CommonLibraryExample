package com.sibin.commonlib.network

import com.sibin.commonlib.NetworkHelper
import okhttp3.RequestBody

@JvmSuppressWildcards
object ApiClass : ApiService {

    private val apiService by lazy { NetworkHelper.getRetrofit().create(ApiService::class.java) }

    override fun postApi(url: String, request: Any) = apiService.postApi(url, request)

    override fun postMultiPartApi(
        url: String,
        partMap: Map<String, RequestBody>
    ) = apiService.postMultiPartApi(url, partMap)


    override fun getApi(url: String) = apiService.getApi(url)

    override fun getApiOb(url: String) = apiService.getApiOb(url)

    override fun patchApi(url: String, request: Any) = apiService.patchApi(url, request)

    override fun putApi(url: String, request: Any) = apiService.putApi(url, request)

    override fun deleteApi(url: String) = apiService.deleteApi(url)

}