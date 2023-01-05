package android.example.ebankingmobile.utils

import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import android.example.ebankingmobile.R
import android.example.ebankingmobile.retrofit.session.SessionManager
import android.widget.Toast

class FrontUtils {
    companion object {
        fun checkUserLoggedIn(context: Context): Boolean {
            val sharedPreferences: SharedPreferences =
                context.getSharedPreferences(context.getString(R.string.app_name), MODE_PRIVATE)

            if (sharedPreferences.getString(SessionManager.USER_TOKEN, "")
                    .toString() == ""
            ) return false
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
                if (it.isEmpty()) {
                    finalBoolean = false
                    return false
                }
            }
            return finalBoolean
        }

        fun removingInformationFromSharedPreferences(context: Context) {
            val sharedPreferences: SharedPreferences =
                context.getSharedPreferences(context.getString(R.string.app_name), MODE_PRIVATE)
            val myEdit = sharedPreferences.edit()

            sharedPreferences.all.forEach { _ ->
                myEdit.clear()
            }

            myEdit.apply()
        }

    }
}