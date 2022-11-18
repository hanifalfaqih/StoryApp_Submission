package id.allana.storyapp_submission.ui.detailstory

import android.os.Bundle
import androidx.core.view.isVisible
import coil.load
import id.allana.storyapp_submission.R
import id.allana.storyapp_submission.base.arch.BaseActivity
import id.allana.storyapp_submission.data.network.model.response.story.StoryItem
import id.allana.storyapp_submission.databinding.ActivityDetailStoryBinding
import id.allana.storyapp_submission.util.StringUtil

class DetailStoryActivity : BaseActivity<ActivityDetailStoryBinding, DetailStoryViewModel>(
    ActivityDetailStoryBinding::inflate
), DetailStoryContract.View {

    companion object {
        const val DETAIL_STORY = "DETAIL_STORY"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getIntentData()
    }

    override fun initView() {
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = getString(R.string.title_detail_story)
    }

    override fun getIntentData() {
        val data = intent.getParcelableExtra<StoryItem>(DETAIL_STORY)
        data?.let { setData(it) }
    }


    override fun showData(isVisible: Boolean) {
        getViewBinding().groupContent.isVisible = isVisible
    }

    override fun setData(data: StoryItem) {
        val uploadDate = data.createdAt?.let { StringUtil.convertTimeData(it) }
        getViewBinding().ivStoryDetail.load(data.photoUrl) { crossfade(true) }
        getViewBinding().tvUploadDateDetail.text = uploadDate
        getViewBinding().tvUsernameDetail.text = data.name
        getViewBinding().tvDescriptionDetail.text = data.description
    }

}