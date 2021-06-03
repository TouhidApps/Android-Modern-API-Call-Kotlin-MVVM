package com.touhidapps.modernApi.modules

import com.google.gson.Gson
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module // Tell the hilt that this object is a module
@InstallIn(SingletonComponent::class) // This will alive for entire app lifecycle
object AppModule {

    /**
     * If you need to convert data class to json
     * @Use: AppModule.gson
     */

    @Singleton
    val gson: Gson = Gson()

}