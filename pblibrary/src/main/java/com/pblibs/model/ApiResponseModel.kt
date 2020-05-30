package com.pblibs.model

import com.google.gson.annotations.SerializedName

/**
 * Created by Proggy Blast on 30/5/20 5:20 PM
 */


data class ApiResponseModel(

    @SerializedName("status")
    var status:Boolean,

    @SerializedName("message")
    var message:String,

    @SerializedName("data")
    var dataList:List<Any>

)