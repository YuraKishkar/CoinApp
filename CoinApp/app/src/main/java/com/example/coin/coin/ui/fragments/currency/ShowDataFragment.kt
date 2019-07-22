package com.example.coin.coin.ui.fragments.currency


import android.content.Context
import android.content.Intent
import android.graphics.drawable.AnimatedVectorDrawable
import android.os.Bundle
import android.support.graphics.drawable.AnimatedVectorDrawableCompat
import android.support.v4.app.Fragment
import android.support.v4.view.GravityCompat
import android.support.v4.widget.DrawerLayout
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.util.LayoutDirection
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.arellomobile.mvp.MvpAppCompatFragment
import com.arellomobile.mvp.presenter.InjectPresenter
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.coin.R
import com.example.coin.coin.dto.CurrencyDataLatest
import com.example.coin.coin.ui.mvp.currency.contract.CurrencyView
import com.example.coin.coin.ui.mvp.currency.presenter.CurrencyPresenter
import com.example.coin.coin.ui.activity.login.LoginActivity
import com.example.coin.coin.ui.adapter.RecyclerAdapter
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.tasks.Task
import kotlinx.android.synthetic.main.activity_start.*
import kotlinx.android.synthetic.main.fragment_show_data.*
import kotlinx.android.synthetic.main.fragment_show_data.view.*


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 *
 */
class ShowDataFragment : MvpAppCompatFragment(), CurrencyView {


    private lateinit var mDrawerLayout: DrawerLayout
    private lateinit var mGoogleSignInClient: GoogleSignInClient


    @InjectPresenter
    lateinit var mCurrencyPresenter: CurrencyPresenter

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_show_data, container, false)
        mDrawerLayout = view.drawer_layout
        setHasOptionsMenu(true)
//        App.componentApp().currencyComponent().inject(this)

        mCurrencyPresenter.requestData()
        initToolBar(view)
        initNavigation(view)
        initRecyclerView(view)
        oAuth(view.context)
        return view
    }

    private fun initToolBar(view: View) {

        if (activity is AppCompatActivity) {
            (activity as AppCompatActivity).setSupportActionBar(view.tool_bar_id)

            (activity as AppCompatActivity).supportActionBar?.apply {
                setDisplayHomeAsUpEnabled(true)
                setDisplayShowTitleEnabled(false)
                setHomeAsUpIndicator(R.drawable.ic_menu_black_24dp)
            }
        }
    }

    private fun initRecyclerView(view: View) {
        view.recycler_view_id.setHasFixedSize(true)
        view.recycler_view_id.layoutManager = LinearLayoutManager(view.context)
    }

    private fun initNavigation(view: View) {
        val googleSignInAccount: GoogleSignInAccount? = GoogleSignIn.getLastSignedInAccount(context)

        val navigation = view.navigation_view_id
        val navigationView = navigation?.getHeaderView(0)
        val name = navigationView?.findViewById<TextView>(R.id.name_id)
        val email = navigationView?.findViewById<TextView>(R.id.email_id)
        val image = navigationView?.findViewById<ImageView>(R.id.photo_id)

        if (googleSignInAccount != null) {
            name?.text = googleSignInAccount.displayName
            email?.text = googleSignInAccount.email
            image?.let {
                Glide.with(view.context)
                        .load(googleSignInAccount.photoUrl)
                        .apply(RequestOptions().circleCrop())
                        .into(it)
            }
        }

        navigation?.setNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.log_out_id -> {
                    mDrawerLayout.closeDrawers()
                    mGoogleSignInClient.signOut().addOnCompleteListener { task: Task<Void> ->
                        val intent = Intent(activity, LoginActivity::class.java)
                        startActivity(intent)
                        activity?.let {
                            it.overridePendingTransition(R.anim.fade_in, R.anim.fade_out)
                            it.finish()
                        }
                    }
                    false
                }
                R.id.favorites_id -> {
                    mDrawerLayout.closeDrawers()
                    false
                }
                else -> false
            }
        }
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {

        return when (item?.itemId) {
            android.R.id.home -> {
                mDrawerLayout.openDrawer(GravityCompat.START, true)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }

    }

    private fun oAuth(context: Context) {
        val googleSignInOptions = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build()

        mGoogleSignInClient = GoogleSignIn.getClient(context, googleSignInOptions)

    }


    override fun onShowData(currencyDataUI: List<CurrencyDataUI>) {
        onShowProgress(false)
        recycler_view_id.adapter = context?.let { RecyclerAdapter(currencyDataUI, it) }
    }

    override fun onShowError(error: Throwable) {

    }

    override fun onShowProgress(b: Boolean) {

        if (progress_bar_id.drawable is AnimatedVectorDrawableCompat) {
            val avd = progress_bar_id.drawable as AnimatedVectorDrawableCompat
            if (b) {
                avd.start()
            } else {
                avd.stop()
                progress_bar_id.visibility = View.GONE
            }
        } else if (progress_bar_id.drawable is AnimatedVectorDrawable) {
            val avd = progress_bar_id.drawable as AnimatedVectorDrawable
            if (b) {
                avd.start()
            } else {
                avd.stop()
                progress_bar_id.visibility = View.GONE
            }
        }

    }
}
