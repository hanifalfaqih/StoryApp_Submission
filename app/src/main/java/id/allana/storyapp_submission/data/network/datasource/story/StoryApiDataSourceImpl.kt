package id.allana.storyapp_submission.data.network.datasource.story

import id.allana.storyapp_submission.data.network.model.response.story.AddStoryResponse
import id.allana.storyapp_submission.data.network.model.response.story.ListStoryResponse
import id.allana.storyapp_submission.data.network.model.response.story.StoryItem
import id.allana.storyapp_submission.data.network.service.StoryApiService
import okhttp3.MultipartBody
import okhttp3.RequestBody
import javax.inject.Inject

class StoryApiDataSourceImpl
@Inject constructor(private val storyApiService: StoryApiService): StoryApiDataSource {

    override suspend fun postUserStory(
        file: MultipartBody.Part?,
        description: RequestBody?
    ): AddStoryResponse {
        return storyApiService.postUserStory(file, description)
    }

    override suspend fun getListStory(): ListStoryResponse {
        return storyApiService.getListStory()
    }

    override suspend fun getStoryDetail(id: String): StoryItem {
        return storyApiService.getStoryId(id)
    }
}