package id.allana.storyapp_submission.data.network.model.response.story

import com.google.gson.annotations.SerializedName

data class AddStoryResponse(
    @SerializedName("error")
    val isError : Boolean,
    @SerializedName("message")
    val message : String,
)
