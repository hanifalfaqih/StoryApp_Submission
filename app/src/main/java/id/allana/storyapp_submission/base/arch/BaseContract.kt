package id.allana.storyapp_submission.base.arch

interface BaseContract {

    interface BaseView {
        fun observeData()
        fun showData(isVisible: Boolean)
        fun showLoading(isVisible: Boolean)
        fun showError(isError: Boolean, msg: String? = null)
    }

    interface BaseViewModel {
        fun logResponse(msg: String?)
    }

    interface BaseRepository {
        fun logResponse(msg: String?)
    }
}
