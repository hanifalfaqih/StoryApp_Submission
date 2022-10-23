package id.allana.storyapp_submission.ui.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import id.allana.storyapp_submission.base.arch.BaseViewModelImpl
import id.allana.storyapp_submission.base.model.Resource
import id.allana.storyapp_submission.data.network.model.request.auth.AuthRequest
import id.allana.storyapp_submission.data.network.model.response.auth.User
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel
@Inject constructor(private val repository: LoginRepository): BaseViewModelImpl(), LoginContract.ViewModel{

    private val loginUserLiveData = MutableLiveData<Resource<User>>()

    override fun getLoginResponseLiveData(): LiveData<Resource<User>> = loginUserLiveData

    override fun loginUser(loginRequest: AuthRequest) {
        loginUserLiveData.value = Resource.Loading()
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = repository.postLoginUser(loginRequest)
                viewModelScope.launch(Dispatchers.Main) {
                    loginUserLiveData.value = Resource.Success(response.data)
                }
            } catch (e: Exception) {
                viewModelScope.launch(Dispatchers.IO) {
                    viewModelScope.launch(Dispatchers.Main) {
                        loginUserLiveData.value = Resource.Error(e.message.toString())
                    }
                }
            }
        }
    }

}