package id.allana.storyapp_submission.ui.detailstory

import android.os.Bundle
import android.widget.Toast
import androidx.core.view.isVisible
import coil.load
import dagger.hilt.android.AndroidEntryPoint
import id.allana.storyapp_submission.base.arch.BaseActivity
import id.allana.storyapp_submission.base.model.Resource
import id.allana.storyapp_submission.data.network.model.response.story.StoryItem
import id.allana.storyapp_submission.databinding.ActivityDetailStoryBinding
import id.allana.storyapp_submission.util.StringUtil

@AndroidEntryPoint
class DetailStoryActivity : BaseActivity<ActivityDetailStoryBinding, DetailStoryViewModel>(
    ActivityDetailStoryBinding::inflate
), DetailStoryContract.View {

    companion object {
        const val DETAIL_STORY_ID = "DETAIL_STORY_ID"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getIntentData()
    }

    override fun initView() {
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = "Detail Story"
    }

    override fun getIntentData() {
        getViewModel().setIntentData(intent.extras)
    }

    override fun observeData() {
        getViewModel().getStoryDetailResponse().observe(this) {
            when(it) {
                is Resource.Loading -> {
                    showLoading(true)
                    showError(false)
                    showData(false)
                }
                is Resource.Success -> {
                    showLoading(false)
                    showError(false)
                    it.data?.let { data ->
                        setData(data)
                        Toast.makeText(this, it.toString(), Toast.LENGTH_SHORT).show()
                    }
                    showData(true)
                }
                is Resource.Error -> {
                    showLoading(false)
                    showError(true, "Can't retrieve detail story")
                    showData(false)
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
        val uploadDate = data.createdAt?.let { StringUtil.convertTimeData(it) }
        getViewBinding().ivStory.load(data.photoUrl) { crossfade(true) }
        getViewBinding().tvUploadDate.text = uploadDate
        getViewBinding().tvUsername.text = data.name
        getViewBinding().tvDescription.text = data.description
    }

    override fun showError(isError: Boolean, msg: String?) {
        if (isError) Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
    }
}