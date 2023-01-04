package android.example.ebankingmobile.utils

import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import android.util.Log
import android.widget.Toast

class FrontUtils {
    companion object {
        fun checkUserLoggedIn(context: Context): Boolean {
            val sharedPreferences: SharedPreferences =
                context.getSharedPreferences("MySharedPref", MODE_PRIVATE)

            if (sharedPreferences.getString("username", "").toString() == "") return false
            return true
        }

        fun showToast(context: Context, msg: String) {
            Toast.makeText(
                context,
                msg,
                Toast.LENGTH_SHORT
            ).show()
        }

        fun checkInputsEmptyOrNot(vararg inputs: String): Boolean {
            var finalBoolean = true
            inputs.forEach {
                Log.i("boolean", it)
                if (it.isEmpty()) {
                    finalBoolean = false
                    return false
                }
            }
            return finalBoolean
        }

    }
}