package id.allana.storyapp_submission.ui.addstory

import androidx.lifecycle.LiveData
import id.allana.storyapp_submission.base.arch.BaseContract
import id.allana.storyapp_submission.base.model.Resource
import id.allana.storyapp_submission.data.network.model.response.story.AddStoryResponse
import okhttp3.MultipartBody
import okhttp3.RequestBody

interface AddStoryContract {

    interface View: BaseContract.BaseView {
        fun intentGallery()
        fun intentCamera()
        fun uploadPhoto()
        fun setOnClickListener()
    }

    interface ViewModel: BaseContract.BaseViewModel {
        fun postUploadStory(file: MultipartBody.Part?, description: RequestBody?)
        fun postUploadStoryLiveData(): LiveData<Resource<AddStoryResponse>>
    }

    interface Repository: BaseContract.BaseRepository {
        suspend fun postUploadStory(file: MultipartBody.Part?, description: RequestBody?): AddStoryResponse
    }

}