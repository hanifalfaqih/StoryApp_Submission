package id.allana.storyapp_submission.ui.register

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
import id.allana.storyapp_submission.databinding.ActivityRegisterBinding
import id.allana.storyapp_submission.ui.login.LoginActivity

@AndroidEntryPoint
class RegisterActivity : BaseActivity<ActivityRegisterBinding, RegisterViewModel>(
    ActivityRegisterBinding::inflate
), RegisterContract.View {

    override fun initView() {
        supportActionBar?.hide()
        playAnimation()
        setOnClick()
    }

    override fun setOnClick() {
        getViewBinding().btnRegister.setOnClickListener {
            getViewModel().registerUser(AuthRequest(
                email = getViewBinding().edRegisterEmail.text.toString(),
                name = getViewBinding().edRegisterName.text.toString(),
                password = getViewBinding().edRegisterPassword.text.toString()
            ))
        }

        getViewBinding().tvNavigateToLogin.setOnClickListener {
            navigateToLogin()
        }
    }

    override fun showLoading(isVisible: Boolean) {
        super.showLoading(isVisible)
        getViewBinding().pbLoading.visibility = if (isVisible) View.VISIBLE else View.INVISIBLE
    }

    override fun navigateToLogin() {
        val intent = Intent(this, LoginActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
        startActivity(intent)
        finish()
    }

    override fun playAnimation() {
        val tvRegister = ObjectAnimator.ofFloat(getViewBinding().tvRegister, View.ALPHA, 1f).setDuration(500)
        val etEmail = ObjectAnimator.ofFloat(getViewBinding().edRegisterEmail, View.ALPHA, 1f).setDuration(500)
        val etName = ObjectAnimator.ofFloat(getViewBinding().edRegisterName, View.ALPHA, 1f).setDuration(500)
        val etPassword = ObjectAnimator.ofFloat(getViewBinding().edRegisterPassword, View.ALPHA, 1f).setDuration(500)
        val btnLogin = ObjectAnimator.ofFloat(getViewBinding().btnRegister, View.ALPHA, 1f).setDuration(500)
        val tvToRegister = ObjectAnimator.ofFloat(getViewBinding().tvNavigateToLogin, View.ALPHA, 1f).setDuration(500)

        AnimatorSet().apply {
            playSequentially(tvRegister, etName, etEmail, etPassword, btnLogin, tvToRegister)
            start()
        }
    }

    override fun observeData() {
        getViewModel().getRegisterResponseLiveData().observe(this) { response ->
            when(response) {
                is Resource.Loading -> {
                    showLoading(true)
                }
                is Resource.Success -> {
                    showLoading(false)
                    Toast.makeText(this, getString(R.string.text_msg_success_created_account), Toast.LENGTH_SHORT).show()
                    navigateToLogin()
                }
                is Resource.Error -> {
                    showLoading(false)
                    showError(true, getString(R.string.text_msg_failed_created_account))
                }
            }
        }

    }

    override fun showError(isError: Boolean, msg: String?) {
        super.showError(isError, msg)
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
    }
}