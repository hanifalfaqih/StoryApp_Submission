package id.allana.storyapp_submission.ui.liststory

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.core.app.ActivityOptionsCompat
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import dagger.hilt.android.AndroidEntryPoint
import id.allana.storyapp_submission.R
import id.allana.storyapp_submission.base.arch.BaseActivity
import id.allana.storyapp_submission.base.model.Resource
import id.allana.storyapp_submission.data.network.model.response.story.StoryItem
import id.allana.storyapp_submission.databinding.ActivityListStoryBinding
import id.allana.storyapp_submission.ui.addstory.AddStoryActivity
import id.allana.storyapp_submission.ui.detailstory.DetailStoryActivity
import id.allana.storyapp_submission.ui.login.LoginActivity

@AndroidEntryPoint
class ListStoryActivity : BaseActivity<ActivityListStoryBinding, ListStoryViewModel>(
    ActivityListStoryBinding::inflate
), ListStoryContract.View {

    private lateinit var adapter: ListStoryAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getData()
    }

    override fun getData() {
        getViewModel().getAllStory()
    }

    override fun initView() {
        initList()
        actionAddStory()
    }


    override fun initList() {
        adapter = ListStoryAdapter { data ->
            data.id?.let { id ->
                val intent = Intent(this, DetailStoryActivity::class.java)
                intent.putExtra(DetailStoryActivity.DETAIL_STORY_ID, id)
                startActivity(intent, ActivityOptionsCompat.makeSceneTransitionAnimation(this).toBundle()   )
            }
        }

        getViewBinding().rvStory.apply {
            adapter = this@ListStoryActivity.adapter
            layoutManager = LinearLayoutManager(this@ListStoryActivity)
        }
    }

    override fun actionAddStory() {
        getViewBinding().fabAddStory.setOnClickListener {
            startActivity(Intent(this, AddStoryActivity::class.java))
        }
    }

    override fun observeData() {
        getViewModel().getStoryListLiveData().observe(this) { resource ->
            when (resource) {
                is Resource.Loading -> {
                    showLoading(true)
                    showData(false)
                    showError(false, null)
                }
                is Resource.Success -> {
                    showLoading(false)
                    showData(true)
                    showError(false, null)
                    resource.data?.let { setDataAdapter(it) }
                }
                is Resource.Error -> {
                    showLoading(false)
                    showData(false)
                    showError(true, resource.message)
                }
            }
        }
    }

    override fun showLoading(isVisible: Boolean) {
        getViewBinding().progressBar.isVisible = isVisible
    }

    override fun showError(isError: Boolean, msg: String?) {
        if (isError) Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
    }

    override fun showData(isVisible: Boolean) {
        getViewBinding().rvStory.isVisible = isVisible
    }

    private fun setDataAdapter(data: List<StoryItem>) {
        adapter.setItems(data)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return super.onCreateOptionsMenu(menu)

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.action_logout) {
            showDialogLogout()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun showDialogLogout() {
        MaterialAlertDialogBuilder(this)
            .apply {
                setTitle("Logout")
                setMessage("Do you want to logout?")
                setPositiveButton("Yes") { dialog, _ ->
                    logoutUser()
                    dialog.dismiss()
                }
                setNegativeButton("No") { dialog, _ ->
                    dialog.dismiss()
                }
            }.create().show()
    }

    private fun logoutUser() {
        getViewModel().deleteSession()
        navigateToLogin()
    }

    private fun navigateToLogin() {
        val intent = Intent(this, LoginActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
        startActivity(intent)
        finish()
    }

}