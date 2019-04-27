package org.noandish.library.pageloginexample

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import kotlinx.android.synthetic.main.activity_main.*
import org.noandish.library.loginpage.Login

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        login.onLoginChangeListener = object : Login.OnLoginChangeListener {
            override fun onRegistered(user: String, pass: String, args: Array<EditText>) {
                // when click Register Button
            }

            override fun onSkiped() {
                // when click Skip Button
            }

            override fun onLogined(user: String, pass: String) {
                //when click Login Button
            }
        }
    }
}
