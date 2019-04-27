package org.noandish.library.pageloginexample

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*
import org.noandish.library.loginpage.Login

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        login.inputUsername(Login.TYPE_INPUT_USERNAME_MOBILE)
        login.onLoginChangeListener = object : Login.OnLoginChangeListener {
            override fun onRegistered(user: String, pass: String, args: Array<View>) {
                // when click Register Button
            }

            override fun onSkipped() {
                // when click Skip Button
            }

            override fun onLogged(user: String, pass: String) {
                //when click Login Button
            }
        }
    }
}
