package com.touhidapps.modernApi.repository


import com.touhidapps.modernApi.model.ItemsModel
import com.touhidapps.modernApi.networkService.RetrofitClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn


class ItemRepository() {

    fun getFlowerList(option: String) : Flow<List<ItemsModel>> = flow {
        val p = RetrofitClient.retrofit.getFlowerList(option)
        emit(p)
    }.flowOn(Dispatchers.IO)


}