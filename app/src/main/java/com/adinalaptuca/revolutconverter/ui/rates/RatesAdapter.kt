package com.adinalaptuca.revolutconverter.ui.rates

import android.content.Context
import android.support.v7.widget.AppCompatEditText
import android.support.v7.widget.AppCompatImageView
import android.support.v7.widget.AppCompatTextView
import android.support.v7.widget.RecyclerView
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.adinalaptuca.revolutconverter.R
import com.adinalaptuca.revolutconverter.restmanager.data.RatesResponse
import kotlinx.android.synthetic.main.item_currency_cell.view.*


/**
 * Created by adinalaptuca on Aug, 2019
 */
class RatesAdapter() : RecyclerView.Adapter<RatesAdapter.RatesViewHolder>() {
    private var items: ArrayList<Pair<String, Double>> = arrayListOf()
    private var originalItems: ArrayList<Pair<String, Double>> = arrayListOf()
    private lateinit var context: Context


    override fun getItemCount(): Int {
        return items.size
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RatesAdapter.RatesViewHolder {
        context = parent.context
        return RatesViewHolder(LayoutInflater.from(context).inflate(R.layout.item_currency_cell, parent, false))
    }

    fun setList(response: RatesResponse) {

        items.clear()
        items.add(Pair(response.base, 1.toDouble()))

        response.rates.forEach { (key, value) ->
            items.add(Pair(key, value))
        }

        originalItems.addAll(items)
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: RatesAdapter.RatesViewHolder, position: Int) {

        items[position].let { rate ->
            holder.apply {
                title.text = rate.first
                subtitle.text = if(RatesEnum.getEnumFromValue(rate.first)!=null) context.getString(RatesEnum.getEnumFromValue(rate.first)!!.displayValue) else rate.first

                currenyValue.setText("%.2f".format(rate.second))
                currenyValue.setSelection(if(!currenyValue.text.isNullOrEmpty()) currenyValue.text!!.length else 0)

                icon.setImageDrawable(context.getDrawable(RatesEnum.getEnumFromValue(rate.first)!!.drawableRes))

                if(position == 0){
                    currenyValue.addTextChangedListener(object : TextWatcher {
                        override fun afterTextChanged(s: Editable?) {
                            for(i in position+1..items.size-1){
                                if(!s.isNullOrEmpty()) {
                                    items.set(i, Pair(items[i].first, "%.2f".format(originalItems[i].second * s.toString().toDouble()).toDouble()))
                                    notifyItemChanged(i)
                                }else{
                                    items.set(i, Pair(items[i].first, "%.2f".format(originalItems[i].second).toDouble())) //if empty string, show original
                                    notifyItemChanged(i)
                                }
                            }
                        }
                        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
                        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
                    })
                }
            }
        }
    }

    class RatesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val title: AppCompatTextView = itemView.textTitle
        val subtitle: AppCompatTextView = itemView.textSubtitle
        val currenyValue: AppCompatEditText = itemView.currencyValue
        val icon: AppCompatImageView = itemView.iconFlag

    }
}