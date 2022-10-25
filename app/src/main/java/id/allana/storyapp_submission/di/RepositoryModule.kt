package id.allana.storyapp_submission.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import id.allana.storyapp_submission.data.local.datasource.LocalDataSource
import id.allana.storyapp_submission.data.network.datasource.auth.AuthApiDataSource
import id.allana.storyapp_submission.data.network.datasource.story.StoryApiDataSource
import id.allana.storyapp_submission.ui.addstory.AddStoryRepository
import id.allana.storyapp_submission.ui.detailstory.DetailStoryRepository
import id.allana.storyapp_submission.ui.liststory.ListStoryRepository
import id.allana.storyapp_submission.ui.login.LoginRepository
import id.allana.storyapp_submission.ui.register.RegisterRepository
import id.allana.storyapp_submission.ui.splashscreen.SplashScreenRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideRegisterRepository(authApiDataSource: AuthApiDataSource): RegisterRepository {
        return RegisterRepository(authApiDataSource)
    }

    @Provides
    @Singleton
    fun provideLoginRepository(authApiDataSource: AuthApiDataSource, localDataSource: LocalDataSource): LoginRepository {
        return LoginRepository(authApiDataSource, localDataSource)
    }

    @Provides
    @Singleton
    fun provideListStoryRepository(listStoryDataSource: StoryApiDataSource, localDataSource: LocalDataSource): ListStoryRepository {
        return ListStoryRepository(listStoryDataSource, localDataSource)
    }

    @Provides
    @Singleton
    fun provideAddStoryRepository(addStoryDataSource: StoryApiDataSource): AddStoryRepository {
        return AddStoryRepository(addStoryDataSource)
    }

    @Provides
    @Singleton
    fun provideSplashScreenRepository(localDataSource: LocalDataSource): SplashScreenRepository {
        return SplashScreenRepository(localDataSource)
    }

    @Provides
    @Singleton
    fun provideDetailStoryRepository(detailStoryDataSource: StoryApiDataSource): DetailStoryRepository {
        return DetailStoryRepository(detailStoryDataSource)
    }


}