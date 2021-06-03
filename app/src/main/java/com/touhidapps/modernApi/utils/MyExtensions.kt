package com.touhidapps.modernApi.utils

import android.util.Base64
import java.nio.charset.StandardCharsets

fun String.toBase64Decode(): String {
    return String(Base64.decode(this, Base64.DEFAULT), StandardCharsets.UTF_8)
}




