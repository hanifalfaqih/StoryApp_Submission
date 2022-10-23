package id.allana.storyapp_submission.data.network.model.request.auth

import com.google.gson.annotations.SerializedName

data class AuthRequest(
    @SerializedName("name")
    var name: String? = null,
    @SerializedName("email")
    var email: String? = null,
    @SerializedName("password")
    var password: String? = null
)
