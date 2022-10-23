package id.allana.storyapp_submission.ui.liststory

import id.allana.storyapp_submission.base.arch.BaseRepositoryImpl
import id.allana.storyapp_submission.data.local.datasource.LocalDataSource
import id.allana.storyapp_submission.data.network.datasource.story.StoryApiDataSource
import id.allana.storyapp_submission.data.network.model.response.story.ListStoryResponse
import id.allana.storyapp_submission.data.network.model.response.story.StoryItem
import javax.inject.Inject

class ListStoryRepository
@Inject constructor(
    private val storyApiDataSource: StoryApiDataSource,
    private val localDataSource: LocalDataSource
): BaseRepositoryImpl(), ListStoryContract.Repository {
    override suspend fun getAllStory(): ListStoryResponse = storyApiDataSource.getListStory()

    override fun deleteSession() {
        localDataSource.clearSession()
    }
}