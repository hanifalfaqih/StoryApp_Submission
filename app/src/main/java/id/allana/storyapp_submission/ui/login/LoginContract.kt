package id.allana.storyapp_submission.ui.login

import androidx.lifecycle.LiveData
import id.allana.storyapp_submission.base.arch.BaseContract
import id.allana.storyapp_submission.base.model.Resource
import id.allana.storyapp_submission.data.network.model.request.auth.AuthRequest
import id.allana.storyapp_submission.data.network.model.response.auth.BaseAuthResponse
import id.allana.storyapp_submission.data.network.model.response.auth.User

interface LoginContract {

    interface View: BaseContract.BaseView {
        fun setOnClick()
        fun navigateToRegister()
        fun navigateToHome()
        fun playAnimation()
    }

    interface ViewModel: BaseContract.BaseViewModel {
        fun getLoginResponseLiveData(): LiveData<Resource<User>>
        fun loginUser(loginRequest: AuthRequest)
    }

    interface Repository: BaseContract.BaseRepository {
        suspend fun postLoginUser(loginRequest: AuthRequest): BaseAuthResponse<User>
    }

}