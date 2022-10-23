package id.allana.storyapp_submission.ui.register

import id.allana.storyapp_submission.base.arch.BaseRepositoryImpl
import id.allana.storyapp_submission.data.network.datasource.auth.AuthApiDataSource
import id.allana.storyapp_submission.data.network.model.request.auth.AuthRequest
import id.allana.storyapp_submission.data.network.model.response.auth.BaseAuthResponse
import id.allana.storyapp_submission.data.network.model.response.auth.User
import javax.inject.Inject

class RegisterRepository
@Inject constructor(private val authApiDataSource: AuthApiDataSource): BaseRepositoryImpl(), RegisterContract.Repository {
    override suspend fun postRegisterUser(registerRequest: AuthRequest): BaseAuthResponse<User> {
        return authApiDataSource.postRegisterUser(registerRequest)
    }
}