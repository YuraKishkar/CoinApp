package com.example.coin.coin.ui.activity.currency

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.example.coin.R
import com.example.coin.coin.ui.fragments.currency.ShowDataFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val fragmentTransition = supportFragmentManager.beginTransaction()
        val fragment = ShowDataFragment()
        fragmentTransition.replace(R.id.container_show_data_id, fragment)
        fragmentTransition.commit()
    }
}
