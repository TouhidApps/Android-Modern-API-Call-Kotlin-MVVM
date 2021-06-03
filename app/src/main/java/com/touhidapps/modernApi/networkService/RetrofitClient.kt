package com.touhidapps.modernApi.networkService

import com.google.gson.GsonBuilder
import okhttp3.*
import okio.Buffer
import okio.BufferedSource
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException
import java.util.concurrent.TimeUnit

object RetrofitClient {

    private const val TIME_OUT: Long = 120

    private val gson = GsonBuilder().setLenient().create()

    private val okHttpClient = OkHttpClient.Builder()
        .readTimeout(TIME_OUT, TimeUnit.SECONDS)
        .connectTimeout(TIME_OUT, TimeUnit.SECONDS)
        .addInterceptor(object : Interceptor {

            override fun intercept(chain: Interceptor.Chain): Response {

                return try {
                    val resp = chain.proceed(chain.request()) // .newBuilder().build()
                    // Deal with the response code
                    if (resp.code == 200) {
                        try {
                            val myJson = resp.peekBody(2048)
                                .string() // peekBody() will not close the response
                            println(myJson)
                        } catch (e: Exception) {
                            // e.printStackTrace()
                            println("Error parse json from intercept..............")
                        }
                    } else { // ইরর কোড হলে ফায়ারবেস ডেটাবেসে লগ পড়বে
                        println(resp)
                    }
                    return resp

                } catch (e: IOException) {
                    e.printStackTrace()

                    // যখন এপিঅাই হিট হবে তখন যেনো মাল্টিপল রিকোয়েস্ট না যায়
//                    if (!chain.request().url.toString().contains("store")) {
//                        chain.proceed(chain.request())
//                    } else {
                        okhttp3.Response.Builder()
                            .code(418)
                            .request(chain.request())
                            .body(object : ResponseBody() {
                                override fun contentLength() = 0L
                                override fun contentType(): MediaType? = null
                                override fun source(): BufferedSource = Buffer()
                            })
                            .message(e.message ?: e.toString())
                            .protocol(Protocol.HTTP_1_1)
                            .build()
//                    }

                }

            }

        })
        .build()

    val retrofit: RetrofitInterface by lazy {
        Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create(gson))
            .baseUrl(AllApi.BASE_URL)
            .client(okHttpClient)
            .build().create(RetrofitInterface::class.java)
    }

}
