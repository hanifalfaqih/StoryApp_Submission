package id.allana.storyapp_submission.data.network.datasource.auth

import id.allana.storyapp_submission.data.network.model.request.auth.AuthRequest
import id.allana.storyapp_submission.data.network.model.response.auth.BaseAuthResponse
import id.allana.storyapp_submission.data.network.model.response.auth.User
import id.allana.storyapp_submission.data.network.service.AuthApiService
import javax.inject.Inject

class AuthApiDataSourceImpl
@Inject constructor(private val authApiService: AuthApiService): AuthApiDataSource {
    override suspend fun postRegisterUser(registerRequest: AuthRequest): BaseAuthResponse<User> {
        return authApiService.postRegisterUser(registerRequest)
    }

    override suspend fun postLoginUser(loginRequest: AuthRequest): BaseAuthResponse<User> {
        return authApiService.postLoginUser(loginRequest)
    }

}