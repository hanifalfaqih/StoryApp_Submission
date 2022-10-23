package id.allana.storyapp_submission.di

import android.content.Context
import com.chuckerteam.chucker.api.ChuckerInterceptor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import id.allana.storyapp_submission.data.local.datasource.LocalDataSource
import id.allana.storyapp_submission.data.network.service.AuthApiService
import id.allana.storyapp_submission.data.network.service.StoryApiService
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Singleton
    @Provides
    fun provideStoryAppService(localDataSource: LocalDataSource, chuckerInterceptor: ChuckerInterceptor): StoryApiService {
        return StoryApiService.invoke(localDataSource, chuckerInterceptor)
    }

    @Singleton
    @Provides
    fun provideAuthApiService(): AuthApiService {
        return AuthApiService.invoke()
    }

    @Singleton
    @Provides
    fun provideChuckerInterceptor(@ApplicationContext context : Context) : ChuckerInterceptor {
        return ChuckerInterceptor.Builder(context).build()
    }

}