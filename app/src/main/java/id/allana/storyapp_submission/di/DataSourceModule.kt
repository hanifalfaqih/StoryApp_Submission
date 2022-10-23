package id.allana.storyapp_submission.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import id.allana.storyapp_submission.data.local.SessionPreference
import id.allana.storyapp_submission.data.local.datasource.LocalDataSource
import id.allana.storyapp_submission.data.local.datasource.LocalDataSourceImpl
import id.allana.storyapp_submission.data.network.datasource.auth.AuthApiDataSource
import id.allana.storyapp_submission.data.network.datasource.auth.AuthApiDataSourceImpl
import id.allana.storyapp_submission.data.network.datasource.story.StoryApiDataSource
import id.allana.storyapp_submission.data.network.datasource.story.StoryApiDataSourceImpl
import id.allana.storyapp_submission.data.network.service.AuthApiService
import id.allana.storyapp_submission.data.network.service.StoryApiService
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataSourceModule {
    @Singleton
    @Provides
    fun provideAuthDataSource(authApiService: AuthApiService): AuthApiDataSource {
        return AuthApiDataSourceImpl(authApiService)
    }

    @Singleton
    @Provides
    fun provideStoryDataSource(storyApiService: StoryApiService): StoryApiDataSource {
        return StoryApiDataSourceImpl(storyApiService)
    }

    @Singleton
    @Provides
    fun provideLocalDataSource(sessionPreference: SessionPreference): LocalDataSource {
        return LocalDataSourceImpl(sessionPreference)
    }
}