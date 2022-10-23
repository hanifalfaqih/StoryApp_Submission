package id.allana.storyapp_submission.util.customview

import android.content.Context
import android.graphics.Canvas
import android.graphics.drawable.Drawable
import android.text.Editable
import android.text.InputType
import android.text.TextWatcher
import android.text.method.PasswordTransformationMethod
import android.text.method.TransformationMethod
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatEditText
import androidx.core.content.ContextCompat
import id.allana.storyapp_submission.R
import id.allana.storyapp_submission.util.StringUtil

class CustomEditTextEmail: AppCompatEditText {

    private lateinit var errorImageDrawable: Drawable

    constructor(context: Context): super(context) {
        init()
    }

    constructor(context: Context, attrs: AttributeSet): super(context, attrs) {
        init()
    }

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int): super(context, attrs, defStyleAttr) {
        init()
    }

    private fun init() {
        errorImageDrawable = ContextCompat.getDrawable(context, R.drawable.ic_baseline_error) as Drawable
        inputType = InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS
        compoundDrawablePadding = 16

        setHint(R.string.text_hint_enter_email)

        addTextChangedListener(object: TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                error = if (s.isNullOrEmpty() && s?.let { StringUtil.isEmailValid(it) } == true) context.getString(R.string.text_email_error) else null
            }

            override fun afterTextChanged(s: Editable?) { }

        })

    }
}