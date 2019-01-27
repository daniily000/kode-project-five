package com.daniily.kodeprojectfive

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import com.daniily.kodeprojectfive.user.STATUS_OK
import kotlinx.android.synthetic.main.activity_login.*

const val LOGIN_OK = 0x00000000
const val LOGIN_REFUSED = 0x00000001

private const val FIELD_OK = 0x00000000
private const val FIELD_LOGIN_IS_EMPTY = 0x00000001
private const val FIELD_PSSWD_IS_EMPTY = 0x00000010
private const val FIELD_BOTH_ARE_EMPTY = FIELD_LOGIN_IS_EMPTY or FIELD_PSSWD_IS_EMPTY

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        button_login.setOnClickListener { v ->

            when (checkInput()) {

                FIELD_OK -> {
                    // A place for 'loading' animation - asynchronous call
                    loginController.performLogin(
                        login_edit_text.text.toString(),
                        password_edit_text.text.toString()
                    ) {
                        when (it) {
                            STATUS_OK -> {
                                setResult(LOGIN_OK)
                                finish()
                            }
                            else -> {
                                Snackbar.make(v, "Login failed. Try again.", Snackbar.LENGTH_INDEFINITE).show()
                            }
                        }
                    }
                }

                FIELD_LOGIN_IS_EMPTY -> {
                    Snackbar.make(v, "Login field is empty! Please fill it.", Snackbar.LENGTH_LONG).show()
                }

                FIELD_PSSWD_IS_EMPTY -> {
                    Snackbar.make(v, "Password field is empty! Please fill it.", Snackbar.LENGTH_LONG).show()
                }

                FIELD_BOTH_ARE_EMPTY -> {
                    Snackbar.make(v, "Login and password fields are empty! Please fill it.", Snackbar.LENGTH_LONG).show()
                }
            }
        }

        login_edit_text.setOnFocusChangeListener{_, hasFocus ->
            if (!hasFocus) login_input.error = null
        }

        password_edit_text.setOnFocusChangeListener { _, hasFocus ->
            if (!hasFocus) password_input.error = null
        }

        setResult(LOGIN_REFUSED)
    }

    private fun checkInput(): Int {

        login_input.error = null
        password_input.error = null

        var fieldState = FIELD_OK

        if (login_edit_text.text?.isEmpty() == true) {
            fieldState = fieldState or FIELD_LOGIN_IS_EMPTY
            login_input.error = "This field should not be empty"
        }
        if (password_edit_text.text?.isEmpty() == true) {
            fieldState = fieldState or FIELD_PSSWD_IS_EMPTY
            password_input.error = "This field should not be empty"
        }

        return fieldState
    }

}
