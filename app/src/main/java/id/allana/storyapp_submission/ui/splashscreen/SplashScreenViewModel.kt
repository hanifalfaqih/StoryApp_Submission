package id.allana.storyapp_submission.ui.splashscreen

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import id.allana.storyapp_submission.base.arch.BaseViewModelImpl
import id.allana.storyapp_submission.base.model.Resource
import id.allana.storyapp_submission.data.network.model.response.auth.User
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashScreenViewModel
@Inject constructor(private val repository: SplashScreenRepository)
    : BaseViewModelImpl(), SplashScreenContract.ViewModel {


    override fun isUserLoggedIn(): Boolean {
        return repository.isUserLoggedIn()
    }
}