package com.example.coin.coin.ui.adapter

import android.content.Context
import android.graphics.drawable.Drawable
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

import com.example.coin.R
import com.example.coin.coin.dto.CurrencyDataLatest
import com.example.coin.coin.dto.Data
import com.example.coin.coin.ui.fragments.currency.CurrencyDataUI
import kotlinx.android.synthetic.main.activity_start.view.*
import kotlinx.android.synthetic.main.item_currency.view.*


class RecyclerAdapter(private val mCurrencylist: List<CurrencyDataUI>, private val mContext: Context) :
    RecyclerView.Adapter<RecyclerAdapter.ViewHolder>() {


    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {


        fun bind(currencyDataUI: CurrencyDataUI, context: Context) {
            itemView.name_item_id.text = currencyDataUI.name
            itemView.short_name_item_id.text = "(${currencyDataUI.symbol})"
            itemView.price_item_id.text = "USD: ${currencyDataUI.price}"
            itemView.volume_item_id.text = "Volume: ${currencyDataUI.volume_24h}"
            Glide.with(context)
                .load(currencyDataUI.url)
                .apply(RequestOptions().circleCrop())
                .into(itemView.item_icon_id)

            if (currencyDataUI.percent_change_24h > 0) {
                itemView.change_item_id.setImageDrawable(itemView.context.getDrawable(R.drawable.ic_arrow_upward_24dp))
            }
        }

    }


    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): RecyclerAdapter.ViewHolder {
        val view = LayoutInflater.from(p0.context)
            .inflate(R.layout.item_currency, p0, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = mCurrencylist.count()

    override fun onBindViewHolder(p0: RecyclerAdapter.ViewHolder, p1: Int) {
//        p0.itemView.name_item_id.text = mCurrencylist.data[p1].name
//        p0.itemView.short_name_item_id.text = mCurrencylist.data[p1].symbol
//        p0.itemView.price_item_id.text = mCurrencylist.data[p1].quote.USD.price.toString()
//        p0.itemView.volume_item_id.text = mCurrencylist.data[p1].quote.USD.volume_24h.toString()
//        p0.bind(mCurrencylist.data[p1])
        p0.bind(mCurrencylist[p1], mContext)

    }

}