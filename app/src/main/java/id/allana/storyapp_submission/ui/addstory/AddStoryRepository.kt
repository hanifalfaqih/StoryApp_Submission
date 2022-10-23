package id.allana.storyapp_submission.ui.addstory

import id.allana.storyapp_submission.base.arch.BaseRepositoryImpl
import id.allana.storyapp_submission.data.network.datasource.story.StoryApiDataSource
import id.allana.storyapp_submission.data.network.model.response.story.AddStoryResponse
import okhttp3.MultipartBody
import okhttp3.RequestBody
import javax.inject.Inject

class AddStoryRepository
@Inject constructor(
    private val storyApiDataSource: StoryApiDataSource
): BaseRepositoryImpl(), AddStoryContract.Repository{

    override suspend fun postUploadStory(file: MultipartBody.Part?, description: RequestBody?): AddStoryResponse {
        return storyApiDataSource.postUserStory(file, description)
    }
}