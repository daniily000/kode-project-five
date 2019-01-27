package com.daniily.kodeprojectfive.user

const val STATUS_OK = 0x00000000
const val STATUS_FAILED = 0x00000001
const val STATUS_USER_DEFINED = 0x00000002


private const val KEY_LOGGED_IN = "logged_in"


interface LoginController {

    fun performLogin(login: String, password: String, callback: ((Int) -> Unit)? = null)
    fun performLogin(login: String, password: String, callback: Callback) =
        performLogin(login, password, callback::onFinish)


    fun performLogout(callback: ((Int) -> Unit)? = null)
    fun performLogout(callback: Callback) =
        performLogout(callback::onFinish)

    fun isLoggedIn(): Boolean

    interface Callback {

        fun onFinish(resultCode: Int)

    }

}