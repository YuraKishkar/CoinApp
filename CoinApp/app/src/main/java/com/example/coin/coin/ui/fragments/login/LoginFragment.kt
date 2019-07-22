package com.example.coin.coin.ui.fragments.login


import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.example.coin.R
import com.example.coin.coin.ui.activity.currency.MainActivity
import com.example.coin.coin.ui.activity.login.LoginActivity
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.SignInButton
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.Task
import kotlinx.android.synthetic.main.fragment_login.view.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 *
 */

fun Fragment.toast(message: CharSequence, duration: Int = android.widget.Toast.LENGTH_SHORT) {
    Toast.makeText(activity, message, duration).show()
}

class LoginFragment : Fragment() {

    private val RC_SIGN_IN = 11
    private lateinit var mGoogleSignInClient: GoogleSignInClient

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_login, container, false)
        view.btn_sign_id.setSize(SignInButton.SIZE_ICON_ONLY)
        view.btn_sign_id.setOnClickListener { signIn() }

        val animTextView = AnimationUtils.loadAnimation(context, R.anim.anim_tv_login)
        view.tv_sign_id.startAnimation(animTextView)
        oAuth(view.context)
        return view
    }

    private fun oAuth(context: Context) {

        val googleSignInOptions = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build()

        mGoogleSignInClient = GoogleSignIn.getClient(context, googleSignInOptions)


    }

    private fun signIn() {
        val signIntent = mGoogleSignInClient.signInIntent
        startActivityForResult(signIntent, RC_SIGN_IN)

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == RC_SIGN_IN) {
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            handleSignInResult(task)
        }
    }

    private fun handleSignInResult(completedTask: Task<GoogleSignInAccount>) {
        try {
            val account = completedTask.getResult(ApiException::class.java)
            updateUI(account)
        } catch (e: ApiException) {
            updateUI(null)
        }
    }

    private fun updateUI(account: GoogleSignInAccount?) {
        if (account != null) {
            val intent = Intent(activity, MainActivity::class.java)
            startActivity(intent)
            activity?.let {
                it.overridePendingTransition(R.anim.fade_in, R.anim.fade_out)
                it.finish()
            }
        } else {
            toast("Error")
        }
    }


}
