package com.template.data.api

import com.google.gson.annotations.SerializedName
import com.template.presentation.util.Config

abstract class Request {
    @SerializedName("api_token")
    var api_token: String = Config.API_KEY
}