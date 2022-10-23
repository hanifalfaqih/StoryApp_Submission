package id.allana.storyapp_submission.data.local.datasource

import id.allana.storyapp_submission.data.local.SessionPreference
import id.allana.storyapp_submission.data.network.model.response.auth.User
import javax.inject.Inject

class LocalDataSourceImpl
@Inject constructor(private val sessionPreference: SessionPreference): LocalDataSource {
    override fun getAuthToken(): String? {
        return sessionPreference.authToken
    }

    override fun setAuthToken(authToken: String?) {
        sessionPreference.authToken = authToken
    }

    override fun isUserLoggedIn(): Boolean {
        return !sessionPreference.authToken.isNullOrEmpty()
    }

    override fun clearSession() {
        sessionPreference.deleteSession()
    }
}