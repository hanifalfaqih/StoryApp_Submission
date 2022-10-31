package id.allana.storyapp_submission.util

import android.text.TextUtils
import android.util.Patterns
import java.text.SimpleDateFormat
import java.util.*

object StringUtil {
    fun isEmailValid(input : CharSequence) : Boolean{
        return if(TextUtils.isEmpty(input)){
            false
        }else{
            Patterns.EMAIL_ADDRESS.matcher(input).matches()
        }
    }

    fun convertTimeData(string: String): String? {
        val fromFormat = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'"
        val toFormat = "dd-MM-yyyy HH:mm"
        val parser = SimpleDateFormat(fromFormat, Locale.ROOT)
        val formatter = SimpleDateFormat(toFormat, Locale.ROOT)

        return parser.parse(string)?.let { formatter.format(it) }
    }

}