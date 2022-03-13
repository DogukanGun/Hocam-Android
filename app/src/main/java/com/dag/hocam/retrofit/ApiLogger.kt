package com.dag.hocam.retrofit
import android.util.Log
import com.google.gson.GsonBuilder
import com.google.gson.JsonParser
import okhttp3.logging.HttpLoggingInterceptor
class ApiLogger :HttpLoggingInterceptor.Logger {
    override fun log(message: String) {
        val logTag="ApiLogger"
        if(message.startsWith("[") && message.endsWith("]")){
            val json = GsonBuilder()
                .setPrettyPrinting()
                .create()
                .toJson(JsonParser().parse(message))
            Log.d(logTag,json)
        }else{
            Log.d(logTag,message)
        }
    }

}