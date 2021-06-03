package com.touhidapps.modernApi.networkService

import com.touhidapps.modernApi.model.ItemsModel
import retrofit2.http.*

interface RetrofitInterface {

    @GET(AllApi.WELCOME_MESSAGE)
    suspend fun getFlowerList(@Query("option") option: String): List<ItemsModel>

}













