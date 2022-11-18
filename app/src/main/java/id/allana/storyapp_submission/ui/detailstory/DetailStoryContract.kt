package id.allana.storyapp_submission.ui.detailstory

import id.allana.storyapp_submission.base.arch.BaseContract
import id.allana.storyapp_submission.data.network.model.response.story.StoryItem

interface DetailStoryContract {

    interface ViewModel: BaseContract.BaseViewModel

    interface View: BaseContract.BaseView {
        fun setData(data: StoryItem)
        fun getIntentData()
    }
    
}