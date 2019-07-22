package com.example.coin.coin.ui.activity.splash

import android.content.Intent
import android.graphics.drawable.AnimatedVectorDrawable
import android.os.Bundle
import android.os.Handler
import android.support.graphics.drawable.AnimatedVectorDrawableCompat
import android.support.v7.app.AppCompatActivity
import com.example.coin.R
import com.example.coin.coin.ui.activity.currency.MainActivity
import com.example.coin.coin.ui.activity.login.LoginActivity
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import kotlinx.android.synthetic.main.activity_start.*

class StartActivity : AppCompatActivity() {

    private val mHandler = Handler()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_start)

    }

    override fun onResume() {
        super.onResume()
        if (image_logo_id.drawable is AnimatedVectorDrawableCompat) {
            val avd = image_logo_id.drawable as AnimatedVectorDrawableCompat
            avd.start()
        } else if (image_logo_id.drawable is AnimatedVectorDrawable) {
            val avd = image_logo_id.drawable as AnimatedVectorDrawable
            avd.start()
        }

        mHandler.postDelayed({
            run {
                val googleSignInAccount: GoogleSignInAccount? = GoogleSignIn.getLastSignedInAccount(this)
                if (checkAccount(googleSignInAccount)) {
                    val intent = Intent(this, MainActivity::class.java)
                    startActivity(intent)
                    overridePendingTransition(R.anim.fade_in, R.anim.fade_out)
                    finish()
                } else {
                    val intent = Intent(this, LoginActivity::class.java)
                    startActivity(intent)
                    overridePendingTransition(R.anim.fade_in, R.anim.fade_out)
                    finish()
                }
            }

        }, 2000)


    }

    private fun checkAccount(g: GoogleSignInAccount?) = g != null

}




