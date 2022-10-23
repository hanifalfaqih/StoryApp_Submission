package id.allana.storyapp_submission.data.network.model.response.auth

import com.google.gson.annotations.SerializedName

data class BaseAuthResponse<D>(
    @SerializedName("error")
    val isError : Boolean,
    @SerializedName("message")
    val message : String,
    @SerializedName("loginResult")
    val data : D
)