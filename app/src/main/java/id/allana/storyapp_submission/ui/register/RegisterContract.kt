package id.allana.storyapp_submission.ui.register

import androidx.lifecycle.LiveData
import id.allana.storyapp_submission.base.arch.BaseContract
import id.allana.storyapp_submission.base.model.Resource
import id.allana.storyapp_submission.data.network.model.request.auth.AuthRequest
import id.allana.storyapp_submission.data.network.model.response.auth.BaseAuthResponse
import id.allana.storyapp_submission.data.network.model.response.auth.User

interface RegisterContract {

    interface View: BaseContract.BaseView {
        fun setOnClick()
        fun navigateToLogin()
        fun playAnimation()
    }

    interface ViewModel: BaseContract.BaseViewModel {
        fun getRegisterResponseLiveData(): LiveData<Resource<User>>
        fun registerUser(registerRequest: AuthRequest)
    }

    interface Repository: BaseContract.BaseRepository {
        suspend fun postRegisterUser(registerRequest: AuthRequest): BaseAuthResponse<User>
    }

}