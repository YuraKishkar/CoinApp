package com.example.coin.coin.ui.activity.login

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.example.coin.R
import com.example.coin.coin.ui.fragments.login.LoginFragment

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val fragmentTransaction = supportFragmentManager.beginTransaction()
        val fragmentLogin = LoginFragment()
        fragmentTransaction.replace(R.id.frame_container_login_id, fragmentLogin)
        fragmentTransaction.commit()
    }
}
