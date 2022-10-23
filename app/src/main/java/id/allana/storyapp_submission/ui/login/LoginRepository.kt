package id.allana.storyapp_submission.ui.login

import id.allana.storyapp_submission.base.arch.BaseRepositoryImpl
import id.allana.storyapp_submission.data.local.datasource.LocalDataSource
import id.allana.storyapp_submission.data.network.datasource.auth.AuthApiDataSource
import id.allana.storyapp_submission.data.network.model.request.auth.AuthRequest
import id.allana.storyapp_submission.data.network.model.response.auth.BaseAuthResponse
import id.allana.storyapp_submission.data.network.model.response.auth.User
import javax.inject.Inject

class LoginRepository
@Inject constructor(
    private val authApiDataSource: AuthApiDataSource,
    private val localDataSource: LocalDataSource
) : BaseRepositoryImpl(), LoginContract.Repository {

    override suspend fun postLoginUser(loginRequest: AuthRequest): BaseAuthResponse<User> {
        val response = authApiDataSource.postLoginUser(loginRequest)
        if (!response.isError) {
            localDataSource.setAuthToken(response.data.token)
        }
        return response
    }
}