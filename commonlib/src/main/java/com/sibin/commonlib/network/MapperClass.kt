package com.sibin.commonlib.network

import android.util.Log
import com.google.gson.Gson
import com.google.gson.JsonElement
import com.google.gson.reflect.TypeToken

class MapperClass {
    inline fun <reified T> transform(data: JsonElement): T {
        return if (data.isJsonArray) {
            val gson = Gson()
            val jsonString = gson.toJson(data)
            Log.d("jsonString", jsonString)
            val sType = object : TypeToken<T>() {}.type
            gson.fromJson<T>(jsonString, sType)
        } else {
            val model = Gson().fromJson<T>(data, T::class.java)
            model
        }
    }


}