package id.allana.storyapp_submission.data.network.datasource.auth

import id.allana.storyapp_submission.data.network.model.request.auth.AuthRequest
import id.allana.storyapp_submission.data.network.model.response.auth.BaseAuthResponse
import id.allana.storyapp_submission.data.network.model.response.auth.User
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthApiDataSource {

    suspend fun postRegisterUser(registerRequest: AuthRequest): BaseAuthResponse<User>

    suspend fun postLoginUser(loginRequest: AuthRequest): BaseAuthResponse<User>


}