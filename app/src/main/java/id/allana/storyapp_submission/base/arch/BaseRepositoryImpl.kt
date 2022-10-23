package id.allana.storyapp_submission.base.arch

import android.util.Log

open class BaseRepositoryImpl: BaseContract.BaseRepository {
    override fun logResponse(msg: String?) {
        Log.d(BaseRepositoryImpl::class.java.simpleName, msg.orEmpty())
    }
}