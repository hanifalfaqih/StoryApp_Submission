package id.allana.storyapp_submission.ui.detailstory

import android.widget.Toast
import androidx.core.view.isVisible
import coil.load
import id.allana.storyapp_submission.base.arch.BaseActivity
import id.allana.storyapp_submission.base.model.Resource
import id.allana.storyapp_submission.data.network.model.response.story.StoryItem
import id.allana.storyapp_submission.databinding.ActivityDetailStoryBinding
import id.allana.storyapp_submission.util.StringUtil

class DetailStoryActivity : BaseActivity<ActivityDetailStoryBinding, DetailStoryViewModel>(
    ActivityDetailStoryBinding::inflate
), DetailStoryContract.View {

    companion object {
        const val DETAIL_STORY_ID = "DETAIL_STORY_ID"
    }

    override fun initView() {
        getIntentData()
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = "Detail Story"
    }

    override fun getIntentData() {
        intent.extras?.let { getViewModel().setIntentData(it) }
    }

    override fun observeData() {
        getViewModel().getStoryDetailResponse().observe(this) { resource ->
            when (resource) {
                is Resource.Loading -> {
                    showLoading(true)
                    showError(false, null)
                    showData(false)
                }
                is Resource.Success -> {
                    showLoading(false)
                    showError(false, null)
                    showData(true)
                    resource.data?.let { setData(it) }
                }
                is Resource.Error -> {
                    showLoading(false)
                    showData(false)
                    showError(true, "Can't retrieved detail story")
                }
            }
        }
        getViewModel().getStoryId().observe(this) {
            getViewModel().getStoryDetail(it)
        }
    }

    override fun showData(isVisible: Boolean) {
        getViewBinding().groupContent.isVisible = isVisible
    }

    override fun showLoading(isVisible: Boolean) {
        getViewBinding().pbLoading.isVisible = isVisible
    }

    override fun setData(data: StoryItem) {
        getViewBinding().ivStory.load(data.photoUrl) {
            crossfade(true)
        }

        val uploadDate = data.createdAt?.let { StringUtil.convertTimeData(it) }
        getViewBinding().tvUploadDate.text = uploadDate

        getViewBinding().tvUsername.text = data.name
        getViewBinding().tvDescription.text = data.description
    }

    override fun showError(isError: Boolean, msg: String?) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
    }
}