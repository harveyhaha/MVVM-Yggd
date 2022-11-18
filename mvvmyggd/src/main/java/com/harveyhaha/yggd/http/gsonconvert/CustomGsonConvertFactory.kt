package com.harveyhaha.yggd.http.gsonconvert

import com.google.gson.Gson
import com.google.gson.TypeAdapter
import com.google.gson.reflect.TypeToken
import okhttp3.RequestBody
import okhttp3.ResponseBody
import org.json.JSONException
import org.json.JSONObject
import retrofit2.Converter
import retrofit2.Retrofit
import java.io.IOException
import java.lang.reflect.Type


/**
 * 自定义错误码处理
 *
 * Usage Example:
 * ```
 *      Retrofit.Builder()
 *      .client(okHttpClient)
 *      .baseUrl(AppConstants.BASE_URL)
 *      .addConverterFactory(ScalarsConverterFactory.create())
 *      .addConverterFactory(CustomGsonConvertFactory.create(object : ServerErrorHandleListener {
 *
 *          override fun needThrow(gson: Gson, jsonObject: JSONObject?, body: String?): IOException? {
 *              var code: Int
 *              try {
 *                  val responseBean = gson.fromJson(body, IResponse::class.java)
 *                  Timber.d("json: ${responseBean.errcode} ${responseBean.msg}")
 *                  code = responseBean.errcode?:ResponseCodeType.UNKNOWN
 *              } catch (e: JsonSyntaxException) {
 *                  code = ResponseCodeType.INCORRECT_FORMAT
 *              }
 *               return if (code == ResponseCodeType.OK) {
 *                   null
 *              } else {
 *                   ServiceErrorException(code, ErrorCode.getErrorCodeContent(context, code))
 *              }
 *           }
 *       }))
 *      .addConverterFactory(GsonConverterFactory.create())
 *      .addCallAdapterFactory(LiveDataCallAdapterFactory())
 *      .build()
 *      .create(HttpServiceApi::class.java)
 *
 *      通用api返回数据格式
 *      open class IResponse(
 *          @ResponseCodeType
 *          var errcode: Int = ResponseCodeType.UNKNOWN,
 *          var msg: String? = null
 *      )
 * ```
 * @Description:
 * @Author:         wutengfei
 * @CreateDate:     2021/1/16 10:38
 */
class CustomGsonConvertFactory private constructor(var listener: ServerErrorHandleListener, var gson: Gson) : Converter.Factory() {
    override fun responseBodyConverter(
        type: Type, annotations: Array<Annotation?>?,
        retrofit: Retrofit?
    ): Converter<ResponseBody, *>? {
        if (type === RequestBody::class.java) {
            return null
        }
        val adapter: TypeAdapter<*> = gson.getAdapter(TypeToken.get(type))
        return GsonResponseBodyConverter(listener, gson, adapter)
    }

    override fun requestBodyConverter(type: Type?, parameterAnnotations: Array<Annotation?>?, methodAnnotations: Array<Annotation?>?, retrofit: Retrofit?): Converter<*, RequestBody>? {
        return null
    }

    companion object {
        fun create(listener: ServerErrorHandleListener?): CustomGsonConvertFactory {
            return create(listener, Gson())
        }

        fun create(serverErrorHandleListener: ServerErrorHandleListener? = null, gson: Gson? = Gson()): CustomGsonConvertFactory {
            var listener: ServerErrorHandleListener? = serverErrorHandleListener
            if (gson == null) throw NullPointerException("gson == null")
            if (listener == null) {
                listener = object : ServerErrorHandleListener {
                    @Throws(JSONException::class)
                    override fun needThrow(gson: Gson, jsonObject: JSONObject?, body: String?): IOException? {
                        return null
                    }
                }
            }
            return CustomGsonConvertFactory(listener, gson)
        }
    }
}