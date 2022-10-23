package id.allana.storyapp_submission.ui.addstory

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import id.allana.storyapp_submission.base.arch.BaseViewModelImpl
import id.allana.storyapp_submission.base.model.Resource
import id.allana.storyapp_submission.data.network.model.response.story.AddStoryResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okhttp3.MultipartBody
import okhttp3.RequestBody
import javax.inject.Inject

@HiltViewModel
class AddStoryViewModel
@Inject constructor(private val repository: AddStoryRepository) : BaseViewModelImpl(),
    AddStoryContract.ViewModel {

    private val postUploadStoryLiveData = MutableLiveData<Resource<AddStoryResponse>>()

    override fun postUploadStory(file: MultipartBody.Part?, description: RequestBody?) {
        postUploadStoryLiveData.value = Resource.Loading()
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = repository.postUploadStory(file, description)
                viewModelScope.launch(Dispatchers.Main) {
                    postUploadStoryLiveData.value = Resource.Success(response)
                }
            } catch (e: Exception) {
                viewModelScope.launch(Dispatchers.Main) {
                    postUploadStoryLiveData.value = Resource.Error(e.message.toString())
                }
            }
        }
    }

    override fun postUploadStoryLiveData(): LiveData<Resource<AddStoryResponse>> = postUploadStoryLiveData
}