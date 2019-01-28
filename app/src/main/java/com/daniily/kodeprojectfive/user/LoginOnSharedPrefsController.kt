package com.daniily.kodeprojectfive.user

import android.content.Context


private const val STORAGE_NAME = "login_info"
private const val KEY_LOGGED_IN = "logged_in"
private const val KEY_LOGIN = "login"
private const val KEY_PASSWORD = "password"
private const val KEY_LOGIN_INFO_SET = "login_set"
private const val SPLITERATOR = "::"

const val STATUS_UNABLE_TO_SAVE_LOGIN = 0x00000002

class LoginOnSharedPrefsController(private val context: Context) : LoginController {


    private var loggedIn: Boolean = false
    private val storage = context.getSharedPreferences(STORAGE_NAME, Context.MODE_PRIVATE)

    private fun purge() {
        storage.edit().clear().apply()
    }

    private fun loadTestData() {
        storage.edit().putStringSet(
            KEY_LOGIN_INFO_SET,
            setOf("test$SPLITERATOR${"test".hashCode()}")
        ).apply()
    }

    init {
        loggedIn = storage.getBoolean(KEY_LOGGED_IN, false)
//        purge()
        loadTestData()
    }

    override fun performLogin(login: String, password: String, callback: ((Int) -> Unit)?) {

        Thread {

            var resultCode =
                if (storage.getStringSet(KEY_LOGIN_INFO_SET, HashSet<String>())?.contains(
                        "$login$SPLITERATOR${password.hashCode()}"
                    ) == true
                )
                    STATUS_OK
                else
                    STATUS_FAILED

            when (resultCode) {

                STATUS_OK -> {
                    loggedIn = true
                    if (!storage.edit().putBoolean(KEY_LOGGED_IN, loggedIn).commit()) resultCode = STATUS_FAILED
                }

            }
            callback?.invoke(resultCode)
        }.run()

    }

    override fun performLogout(callback: ((Int) -> Unit)?) {
        storage.edit().putBoolean(KEY_LOGGED_IN, false).apply()
    }

    override fun isLoggedIn() = loggedIn
}












