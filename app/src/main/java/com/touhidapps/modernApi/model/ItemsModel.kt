package com.touhidapps.modernApi.model

import com.google.gson.annotations.SerializedName

data class ItemsModel (

   @SerializedName("id") var id : String,
   @SerializedName("name") var name : String,
   @SerializedName("details") var details : String,
   @SerializedName("fileName") var fileName : String,
   @SerializedName("baseUrl") var baseUrl : String

)