package com.sibin.commonlib.network


import com.sibin.commonlib.NetworkHelper
import okhttp3.ResponseBody
import retrofit2.Converter
import retrofit2.Retrofit

class ErrorUtils {

    private var retrofit: Retrofit = NetworkHelper.getRetrofit()


    fun parseError(responseErrorBody: ResponseBody?, code: Int): ResourceError {
        val converter: Converter<ResponseBody, ResourceError> =
            retrofit
                .responseBodyConverter(ResourceError::class.java, arrayOfNulls<Annotation>(0));
        var error = ResourceError()
        try {
            error = converter.convert(responseErrorBody)!!
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return checkErrorCode(error)
    }

    private fun checkErrorCode(error: ResourceError): ResourceError {
        return error
    }
}