package id.allana.storyapp_submission.data.network.service

import com.chuckerteam.chucker.api.ChuckerInterceptor
import id.allana.storyapp_submission.BuildConfig
import id.allana.storyapp_submission.data.local.datasource.LocalDataSource
import id.allana.storyapp_submission.data.network.model.response.story.AddStoryResponse
import id.allana.storyapp_submission.data.network.model.response.story.ListStoryResponse
import id.allana.storyapp_submission.data.network.model.response.story.StoryItem
import okhttp3.*
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*
import java.util.concurrent.TimeUnit

interface StoryApiService {

    @Multipart
    @POST("stories")
    suspend fun postUserStory(
        @Part file: MultipartBody.Part?,
        @Part("description") description: RequestBody?,
    ): AddStoryResponse

    @GET("stories")
    suspend fun getListStory(): ListStoryResponse

    @GET("stories/{id}")
    suspend fun getStoryId(@Path("id") id : String): StoryItem

    companion object {

        operator fun invoke(localDataSource: LocalDataSource, chuckerInterceptor: ChuckerInterceptor): StoryApiService {
            val storyInterceptor = Interceptor {
                val requestBuilder = it.request().newBuilder()
                localDataSource.getAuthToken()?.let { token ->
                    requestBuilder.addHeader("Authorization", "Bearer $token")
                }
                it.proceed(requestBuilder.build())
            }

            val okHttpClient = OkHttpClient.Builder()
                .addInterceptor(storyInterceptor)
                .addInterceptor(chuckerInterceptor)
                .connectTimeout(120, TimeUnit.SECONDS)
                .readTimeout(120, TimeUnit.SECONDS)
                .build()

            val retrofit = Retrofit.Builder()
                .baseUrl(BuildConfig.BASE_URL_DICODING_API)
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build()
            return retrofit.create(StoryApiService::class.java)
        }
    }


}