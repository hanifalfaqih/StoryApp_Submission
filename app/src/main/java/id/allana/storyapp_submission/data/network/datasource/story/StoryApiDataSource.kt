package id.allana.storyapp_submission.data.network.datasource.story

import id.allana.storyapp_submission.data.network.model.response.story.AddStoryResponse
import id.allana.storyapp_submission.data.network.model.response.story.ListStoryResponse
import id.allana.storyapp_submission.data.network.model.response.story.StoryItem
import okhttp3.MultipartBody
import okhttp3.RequestBody

interface StoryApiDataSource {

    suspend fun postUserStory(
        file: MultipartBody.Part?,
        description: RequestBody?
    ): AddStoryResponse

    suspend fun getListStory(): ListStoryResponse

    suspend fun getStoryDetail(id: String): StoryItem
}