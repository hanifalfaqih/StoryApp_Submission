package id.allana.storyapp_submission.ui.splashscreen

interface SplashScreenContract {

    interface View {
        fun checkLoginStatus()
        fun navigateToLogin()
        fun navigateToHome()
    }

    interface ViewModel {
        fun isUserLoggedIn(): Boolean
    }

    interface Repository {
        fun isUserLoggedIn(): Boolean
    }

}