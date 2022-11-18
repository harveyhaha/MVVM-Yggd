package com.harveyhaha.yggd.http.gsonconvert

import com.google.gson.Gson
import com.google.gson.TypeAdapter
import okhttp3.ResponseBody
import org.json.JSONException

import org.json.JSONObject
import retrofit2.Converter
import java.io.IOException


/**
 *
 * @Description:
 * @Author:         wutengfei
 * @CreateDate:     2021/1/16 10:39
 */
class GsonResponseBodyConverter<T>(var listener: ServerErrorHandleListener, var gson: Gson, var adapter: TypeAdapter<T>) : Converter<ResponseBody, T> {

    @Throws(IOException::class)
    override fun convert(value: ResponseBody): T? {
        val json: String = value.string()
        return try {
            val jsonOject = JSONObject(json)
            val serverException: IOException? = listener.needThrow(gson, jsonOject, json)
            if (serverException == null) {
                adapter.fromJson(json)
            } else {
                throw serverException
            }
        } catch (e: JSONException) {
            throw ServiceErrorException(e)
        } finally {
            value.close()
        }
    }
}