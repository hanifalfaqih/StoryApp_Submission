package id.allana.storyapp_submission.ui.liststory

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import id.allana.storyapp_submission.base.arch.BaseViewModelImpl
import id.allana.storyapp_submission.base.model.Resource
import id.allana.storyapp_submission.data.network.model.response.story.ListStoryResponse
import id.allana.storyapp_submission.data.network.model.response.story.StoryItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ListStoryViewModel
@Inject constructor(
    private val repository: ListStoryRepository
) : BaseViewModelImpl(), ListStoryContract.ViewModel {

    private val allStoryLiveData = MutableLiveData<Resource<List<StoryItem>>>()

    override fun getStoryListLiveData(): LiveData<Resource<List<StoryItem>>> =
        allStoryLiveData

    override fun getAllStory() {
        allStoryLiveData.value = Resource.Loading()
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = repository.getAllStory()
                viewModelScope.launch(Dispatchers.Main) {
                    allStoryLiveData.value = Resource.Success(response.listStory)
                }
            } catch (e: Exception) {
                viewModelScope.launch(Dispatchers.Main) {
                    allStoryLiveData.value = Resource.Error(e.message.toString())
                }
            }
        }
    }

    override fun deleteSession() {
        repository.deleteSession()
    }

}