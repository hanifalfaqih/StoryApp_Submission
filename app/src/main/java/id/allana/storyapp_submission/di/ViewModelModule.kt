package id.allana.storyapp_submission.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.scopes.ActivityScoped
import id.allana.storyapp_submission.base.arch.GenericViewModelFactory
import id.allana.storyapp_submission.ui.addstory.AddStoryRepository
import id.allana.storyapp_submission.ui.addstory.AddStoryViewModel
import id.allana.storyapp_submission.ui.detailstory.DetailStoryViewModel
import id.allana.storyapp_submission.ui.liststory.ListStoryRepository
import id.allana.storyapp_submission.ui.liststory.ListStoryViewModel
import id.allana.storyapp_submission.ui.login.LoginRepository
import id.allana.storyapp_submission.ui.login.LoginViewModel
import id.allana.storyapp_submission.ui.register.RegisterRepository
import id.allana.storyapp_submission.ui.register.RegisterViewModel
import id.allana.storyapp_submission.ui.splashscreen.SplashScreenRepository
import id.allana.storyapp_submission.ui.splashscreen.SplashScreenViewModel

@Module
@InstallIn(ActivityComponent::class)
object ViewModelModule {

    @Provides
    @ActivityScoped
    fun provideRegisterViewModel(registerRepository: RegisterRepository): RegisterViewModel {
        return GenericViewModelFactory(RegisterViewModel(registerRepository)).create(RegisterViewModel::class.java)
    }

    @Provides
    @ActivityScoped
    fun provideLoginViewModel(loginRepository: LoginRepository): LoginViewModel {
        return GenericViewModelFactory(LoginViewModel(loginRepository)).create(LoginViewModel::class.java)
    }

    @Provides
    @ActivityScoped
    fun provideListStoryViewModel(listStoryRepository: ListStoryRepository): ListStoryViewModel {
        return GenericViewModelFactory(ListStoryViewModel(listStoryRepository)).create(ListStoryViewModel::class.java)
    }

    @Provides
    @ActivityScoped
    fun provideAddStoryViewModel(addStoryRepository: AddStoryRepository): AddStoryViewModel {
        return GenericViewModelFactory(AddStoryViewModel(addStoryRepository)).create(AddStoryViewModel::class.java)
    }

    @Provides
    @ActivityScoped
    fun provideSplashScreenViewModel(splashScreenRepository: SplashScreenRepository): SplashScreenViewModel {
        return GenericViewModelFactory(SplashScreenViewModel(splashScreenRepository)).create(SplashScreenViewModel::class.java)
    }

}