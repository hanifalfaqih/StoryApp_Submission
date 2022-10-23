package id.allana.storyapp_submission.ui.splashscreen

import id.allana.storyapp_submission.base.arch.BaseRepositoryImpl
import id.allana.storyapp_submission.data.local.datasource.LocalDataSource
import id.allana.storyapp_submission.data.network.datasource.auth.AuthApiDataSource
import id.allana.storyapp_submission.data.network.model.response.auth.BaseAuthResponse
import id.allana.storyapp_submission.data.network.model.response.auth.User
import javax.inject.Inject

class SplashScreenRepository
@Inject constructor(
    private val localDataSource: LocalDataSource
): BaseRepositoryImpl(), SplashScreenContract.Repository {
    override fun isUserLoggedIn(): Boolean {
        return localDataSource.isUserLoggedIn()
    }

}