package id.allana.storyapp_submission.data.local.datasource

import id.allana.storyapp_submission.data.network.model.response.auth.User

interface LocalDataSource {
    fun getAuthToken(): String?
    fun setAuthToken(authToken: String?)
    fun isUserLoggedIn(): Boolean
    fun clearSession()
}