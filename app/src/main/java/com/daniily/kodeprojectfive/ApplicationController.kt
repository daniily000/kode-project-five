package com.daniily.kodeprojectfive

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.daniily.kodeprojectfive.user.LoginController
import com.daniily.kodeprojectfive.user.LoginOnSharedPrefsController


private const val LOGIN_REQUEST = 0x00000000
private const val FEED_REQUEST = 0x00000001

lateinit var loginController: LoginController

class ApplicationController : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        loginController = LoginOnSharedPrefsController(this)

        if (!loginController.isLoggedIn()) {
            startLogin()
        } else {
            startFeed()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {

        when (requestCode) {

            LOGIN_REQUEST -> {
                when (resultCode) {
                    LOGIN_OK -> startFeed()
                    else -> finish()
                }
            }
            FEED_REQUEST -> {
                when (resultCode) {
                    NO_LOGOUT -> finish()
                    LOGOUT -> {
                        loginController.performLogout()
                        startLogin()
                    }
                }
            }
            else -> finish()
        }

    }

    private fun startLogin() {
        startActivityForResult(
            Intent(this, LoginActivity::class.java),
            LOGIN_REQUEST
        )
    }

    private fun startFeed() {
        startActivityForResult(
            Intent(this, FeedActivity::class.java),
            FEED_REQUEST
        )
    }


}
