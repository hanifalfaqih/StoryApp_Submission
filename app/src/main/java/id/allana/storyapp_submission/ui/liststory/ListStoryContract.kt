package id.allana.storyapp_submission.ui.liststory

import androidx.lifecycle.LiveData
import id.allana.storyapp_submission.base.arch.BaseContract
import id.allana.storyapp_submission.base.model.Resource
import id.allana.storyapp_submission.data.network.model.response.story.ListStoryResponse
import id.allana.storyapp_submission.data.network.model.response.story.StoryItem

interface ListStoryContract {

    interface View: BaseContract.BaseView {
        fun initList()
        fun getData()
        fun actionAddStory()
    }

    interface ViewModel: BaseContract.BaseViewModel {
        fun getStoryListLiveData(): LiveData<Resource<List<StoryItem>>>
        fun getAllStory()
        fun deleteSession()
    }

    interface Repository: BaseContract.BaseRepository {
        suspend fun getAllStory(): ListStoryResponse
        fun deleteSession()
    }

}