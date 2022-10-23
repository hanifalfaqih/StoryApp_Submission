package id.allana.storyapp_submission.data.network.service

import id.allana.storyapp_submission.BuildConfig
import id.allana.storyapp_submission.data.local.datasource.LocalDataSource
import id.allana.storyapp_submission.data.network.model.request.auth.AuthRequest
import id.allana.storyapp_submission.data.network.model.response.auth.BaseAuthResponse
import id.allana.storyapp_submission.data.network.model.response.auth.User
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.POST
import java.util.concurrent.TimeUnit

interface AuthApiService {

    @POST("register")
    suspend fun postRegisterUser(@Body registerRequest: AuthRequest): BaseAuthResponse<User>

    @POST("login")
    suspend fun postLoginUser(@Body loginRequest: AuthRequest): BaseAuthResponse<User>

    companion object {

        operator fun invoke(): AuthApiService {
            val okHttpClient = OkHttpClient.Builder()
                .connectTimeout(120, TimeUnit.SECONDS)
                .readTimeout(120, TimeUnit.SECONDS)
                .build()

            val retrofit = Retrofit.Builder()
                .baseUrl(BuildConfig.BASE_URL_DICODING_API)
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build()
            return retrofit.create(AuthApiService::class.java)
        }
    }
}