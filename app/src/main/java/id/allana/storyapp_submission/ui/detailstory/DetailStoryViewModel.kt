package id.allana.storyapp_submission.ui.detailstory

import android.os.Bundle
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import id.allana.storyapp_submission.base.arch.BaseViewModelImpl
import id.allana.storyapp_submission.base.model.Resource
import id.allana.storyapp_submission.data.network.model.response.story.StoryItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailStoryViewModel
@Inject constructor(private val repository: DetailStoryRepository): BaseViewModelImpl(), DetailStoryContract.ViewModel {

    private var detailStoryLiveData = MutableLiveData<Resource<StoryItem>>()

    private var detailStoryId = MutableLiveData<String>()

    override fun setIntentData(extras: Bundle) {
        detailStoryId.value = extras.getString(DetailStoryActivity.DETAIL_STORY_ID)
    }

    override fun getStoryId(): LiveData<String> = detailStoryId

    override fun getStoryDetailResponse(): LiveData<Resource<StoryItem>> = detailStoryLiveData

    override fun getStoryDetail(id: String) {
        detailStoryLiveData.value = Resource.Loading()
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = repository.getStoryDetail(id)
                viewModelScope.launch(Dispatchers.Main) {
                    detailStoryLiveData.value = Resource.Success(response)
                }
            } catch (e: Exception) {
                viewModelScope.launch(Dispatchers.Main) {
                    detailStoryLiveData.value = Resource.Error(e.message.toString())
                }
            }
        }
    }

}