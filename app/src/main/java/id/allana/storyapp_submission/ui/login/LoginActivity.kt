package id.allana.storyapp_submission.ui.login

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.content.Intent
import android.view.View
import android.widget.Toast
import dagger.hilt.android.AndroidEntryPoint
import id.allana.storyapp_submission.R
import id.allana.storyapp_submission.base.arch.BaseActivity
import id.allana.storyapp_submission.base.model.Resource
import id.allana.storyapp_submission.data.network.model.request.auth.AuthRequest
import id.allana.storyapp_submission.databinding.ActivityLoginBinding
import id.allana.storyapp_submission.ui.liststory.ListStoryActivity
import id.allana.storyapp_submission.ui.register.RegisterActivity

@AndroidEntryPoint
class LoginActivity : BaseActivity<ActivityLoginBinding, LoginViewModel>(
    ActivityLoginBinding::inflate
), LoginContract.View {

    override fun initView() {
        supportActionBar?.hide()
        playAnimation()
        setOnClick()
    }

    override fun setOnClick() {
        getViewBinding().btnLogin.setOnClickListener {
            getViewModel().loginUser(
                AuthRequest(
                    email = getViewBinding().edRegisterEmail.text.toString(),
                    password = getViewBinding().edRegisterPassword.text.toString()
                )
            )
        }

        getViewBinding().tvNavigateToRegister.setOnClickListener {
            navigateToRegister()
        }
    }

    override fun navigateToRegister() {
        val intent = Intent(this, RegisterActivity::class.java)
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

    override fun playAnimation() {
        val tvLogin = ObjectAnimator.ofFloat(getViewBinding().tvLogin, View.ALPHA, 1f).setDuration(500)
        val etEmail = ObjectAnimator.ofFloat(getViewBinding().edRegisterEmail, View.ALPHA, 1f).setDuration(500)
        val etPassword = ObjectAnimator.ofFloat(getViewBinding().edRegisterPassword, View.ALPHA, 1f).setDuration(500)
        val btnLogin = ObjectAnimator.ofFloat(getViewBinding().btnLogin, View.ALPHA, 1f).setDuration(500)
        val tvToRegister = ObjectAnimator.ofFloat(getViewBinding().tvNavigateToRegister, View.ALPHA, 1f).setDuration(500)

        AnimatorSet().apply {
            playSequentially(tvLogin, etEmail, etPassword, btnLogin, tvToRegister)
            start()
        }
    }


    override fun showError(isError: Boolean, msg: String?) {
        super.showError(isError, msg)
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
    }

    override fun observeData() {
        super.observeData()
        getViewModel().getLoginResponseLiveData().observe(this) { response ->
            when (response) {
                is Resource.Loading -> {
                    showLoading(true)
                }
                is Resource.Success -> {
                    showLoading(false)
                    Toast.makeText(this, getString(R.string.text_msg_login_success), Toast.LENGTH_SHORT).show()
                    navigateToHome()
                }
                is Resource.Error -> {
                    showLoading(false)
                    showError(true, getString(R.string.text_msg_login_failed))
                }
            }
        }
    }

    override fun showLoading(isVisible: Boolean) {
        super.showLoading(isVisible)
        getViewBinding().pbLoading.visibility = if (isVisible) View.VISIBLE else View.INVISIBLE
    }
}