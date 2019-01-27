package com.daniily.kodeprojectfive

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import com.daniily.kodeprojectfive.user.STATUS_OK
import kotlinx.android.synthetic.main.activity_login.*

const val LOGIN_OK = 0x00000000
const val LOGIN_REFUSED = 0x00000001

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        button_login.setOnClickListener {v ->
            loginController.performLogin(
                login_edit_text.text.toString(),
                password_edit_text.text.toString()
            ) {
                when (it) {
                    STATUS_OK -> {
                        Snackbar.make(v, "Logging in...", Snackbar.LENGTH_LONG)
                        setResult(LOGIN_OK)
                        finish()
                    }
                    else -> {
                        Snackbar.make(v, "Wrong pair", Snackbar.LENGTH_LONG)
                    }
                }
            }
        }

        setResult(LOGIN_REFUSED)
    }

}
