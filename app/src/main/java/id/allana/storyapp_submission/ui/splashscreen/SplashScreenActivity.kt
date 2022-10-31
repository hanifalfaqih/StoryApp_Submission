package id.allana.storyapp_submission.ui.splashscreen

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.lifecycle.lifecycleScope
import dagger.hilt.android.AndroidEntryPoint
import id.allana.storyapp_submission.base.arch.BaseActivity
import id.allana.storyapp_submission.databinding.ActivitySpashScreenBinding
import id.allana.storyapp_submission.ui.liststory.ListStoryActivity
import id.allana.storyapp_submission.ui.login.LoginActivity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@SuppressLint("CustomSplashScreen")
@AndroidEntryPoint
class SplashScreenActivity: BaseActivity<ActivitySpashScreenBinding, SplashScreenViewModel>(
    ActivitySpashScreenBinding::inflate
), SplashScreenContract.View{

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        checkLoginStatus()
    }

    override fun initView() {
        supportActionBar?.hide()
    }

    override fun checkLoginStatus() {
        if (getViewModel().isUserLoggedIn()) {
            navigateToHome()
        } else {
            lifecycleScope.launch(Dispatchers.IO) {
                delay(1000)
                lifecycleScope.launch(Dispatchers.Main) {
                    navigateToLogin()
                }
            }
        }
    }

    override fun navigateToLogin() {
        val intent = Intent(this, LoginActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
        startActivity(intent)
        finish()
    }

    override fun navigateToHome() {
        val intent = Intent(this, ListStoryActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
        startActivity(intent)
        finish()
    }
}