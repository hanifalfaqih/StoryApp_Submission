package id.allana.storyapp_submission.ui.register

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import dagger.hilt.android.lifecycle.HiltViewModel
import androidx.lifecycle.viewModelScope
import id.allana.storyapp_submission.base.arch.BaseViewModelImpl
import id.allana.storyapp_submission.base.model.Resource
import id.allana.storyapp_submission.data.network.model.request.auth.AuthRequest
import id.allana.storyapp_submission.data.network.model.response.auth.User
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel
@Inject constructor(private val repository: RegisterRepository): BaseViewModelImpl(), RegisterContract.ViewModel {

    private val registerUserLiveData = MutableLiveData<Resource<User>>()

    override fun getRegisterResponseLiveData(): LiveData<Resource<User>> = registerUserLiveData

    override fun registerUser(registerRequest: AuthRequest) {
        registerUserLiveData.value = Resource.Loading()
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = repository.postRegisterUser(registerRequest)
                viewModelScope.launch(Dispatchers.Main) {
                    registerUserLiveData.value = Resource.Success(response.data)
                }
            } catch (e: Exception) {
                viewModelScope.launch(Dispatchers.Main) {
                    registerUserLiveData.value = Resource.Error(e.message.toString())
                }
            }
        }
    }

}