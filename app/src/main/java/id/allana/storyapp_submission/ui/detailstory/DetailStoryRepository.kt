package id.allana.storyapp_submission.ui.detailstory

import id.allana.storyapp_submission.base.arch.BaseRepositoryImpl
import id.allana.storyapp_submission.data.network.datasource.story.StoryApiDataSource
import id.allana.storyapp_submission.data.network.model.response.story.StoryItem
import javax.inject.Inject

class DetailStoryRepository
@Inject constructor(private val storyApiDataSource: StoryApiDataSource)
    : BaseRepositoryImpl(), DetailStoryContract.Repository {

    override suspend fun getStoryDetail(id: String): StoryItem {
        return storyApiDataSource.getStoryDetail(id)
    }
}