package id.allana.storyapp_submission.data.network.model.response.auth

import com.google.gson.annotations.SerializedName

data class User(
    @SerializedName("_id")
    var id: String?,
    @SerializedName("name")
    var name: String?,
    @SerializedName("email")
    var email: String?,
    @SerializedName("token")
    var token: String?
)
