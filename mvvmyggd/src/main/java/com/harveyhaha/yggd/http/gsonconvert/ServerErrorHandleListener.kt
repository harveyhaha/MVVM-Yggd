package com.harveyhaha.yggd.http.gsonconvert

import com.google.gson.Gson
import org.json.JSONException
import org.json.JSONObject
import java.io.IOException


/**
 *
 * @Description:
 * @Author:         wutengfei
 * @CreateDate:     2021/1/16 10:39
 */
interface ServerErrorHandleListener {
    /**
     * 自定义抛出服务器定义错误
     * @param jsonObject json
     * @param body       body
     * @return 自定义异常抛出
     * @throws JSONException
     */
    @Throws(JSONException::class)
    fun needThrow(gson: Gson, jsonObject: JSONObject?, body: String?): IOException?
}
