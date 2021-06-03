package com.touhidapps.modernApi.networkService

import com.touhidapps.modernApi.utils.toBase64Decode

object AllApi {

    private external fun baseUrlFromJNI(): String

    val BASE_URL = baseUrlFromJNI().toBase64Decode()
// api/demo/jsondemoapi.php?option=3
    private const val V1 = "api/demo/"

    const val WELCOME_MESSAGE = V1 + "jsondemoapi.php"


//    https://touhidapps.com/api/demo/jsondemoapi.php?option=3

}
