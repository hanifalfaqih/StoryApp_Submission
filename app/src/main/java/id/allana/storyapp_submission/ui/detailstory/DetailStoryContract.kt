package id.allana.storyapp_submission.ui.detailstory

import android.os.Bundle
import androidx.lifecycle.LiveData
import id.allana.storyapp_submission.base.arch.BaseContract
import id.allana.storyapp_submission.base.model.Resource
import id.allana.storyapp_submission.data.network.model.response.story.StoryItem

interface DetailStoryContract {

    interface ViewModel: BaseContract.BaseViewModel {
        fun getStoryId(): LiveData<String>
        fun setIntentData(extras: Bundle?)
        fun getStoryDetail(id: String)
        fun getStoryDetailResponse(): LiveData<Resource<StoryItem>>
    }

    interface View: BaseContract.BaseView {
        fun setData(data: StoryItem)
        fun getIntentData()
    }

    interface Repository: BaseContract.BaseRepository {
        suspend fun getStoryDetail(id: String): StoryItem
    }
}