package com.sibin.commonlib.network

import com.google.gson.JsonElement
import io.reactivex.Flowable
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.http.*

@JvmSuppressWildcards
interface ApiService {

    @POST
    fun postApi(@Url url: String, @Body request: Any): Flowable<JsonElement>

    @Multipart
    @POST
    fun postMultiPartApi(
        @Url url: String,
        @PartMap partMap: Map<String, RequestBody>
    ): Flowable<JsonElement>

    @GET
    fun getApi(@Url url: String): Flowable<JsonElement>

    @GET
    fun getApiOb(@Url url: String): Call<JsonElement>

    @PATCH
    fun patchApi(@Url url: String, @Body request: Any): Flowable<JsonElement>

    @PUT
    fun putApi(@Url url: String, @Body request: Any): Flowable<JsonElement>

    @DELETE
    fun deleteApi(@Url url: String): Flowable<JsonElement>
}