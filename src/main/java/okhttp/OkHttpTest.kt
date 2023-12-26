package okhttp

import okhttp3.*
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.RequestBody.Companion.toRequestBody
import okhttp3.logging.HttpLoggingInterceptor
import okio.BufferedSource
import java.util.concurrent.TimeUnit

/**
 * okhttp 源码学习的 https://jun2sn6jvo.feishu.cn/docx/Pqmhdy1YloygShxwBAscDwA8nSe
 * @author：李晓楠
 * 时间：2023/11/28 15:25
 */
object OkHttpTest {

    @JvmStatic
    fun main(args: Array<String>) {
        val build = OkHttpClient.Builder()
            .connectTimeout(60, TimeUnit.SECONDS)
            .readTimeout(60, TimeUnit.SECONDS)
            .writeTimeout(60, TimeUnit.SECONDS)
            .addInterceptor(object : Interceptor {
                override fun intercept(chain: Interceptor.Chain): Response {
                    val response : Response
                            = chain.proceed (chain.request())
                    val source : BufferedSource = response.body!!.source()
                    source.request (Long.MAX_VALUE)
                    return response
                }

            })
        val okHttpClient = build.build()
        val requestUrl = "https://libreplata-peru-gateway.libreplata.com/v1/country1/program/customer/filterPhone"
        val body = "{\"phone\":\"888885558\"}".toRequestBody("application/json;charset=utf-8".toMediaType())
        val request = Request.Builder()
            .url(requestUrl)
            .post(body)
            .addHeader("appId","2")
            .addHeader("deviceId","[C@4231a76")
            .addHeader("osVersion","10")
            .addHeader("appVersion","1.0.0")
            .addHeader("client","android")
            .addHeader("token","")
            .addHeader("language","es")
            .addHeader("time","1701156232855")
            .addHeader("channelName","googleplay")
            .addHeader("channelCode","LibrePlata001")
            .build()
        okHttpClient.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: okhttp3.Call, e: java.io.IOException) {
                println("onFailure")
            }

            override fun onResponse(call: okhttp3.Call, response: okhttp3.Response) {
                println("onResponse")
                if (response.isSuccessful) {
                    val responseBody = response.body
                    val responseString = responseBody?.string()
                    println("str===$responseString")
                }
            }

        })
    }

}