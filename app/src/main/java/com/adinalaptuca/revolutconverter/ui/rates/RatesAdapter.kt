package com.adinalaptuca.revolutconverter.ui.rates

import android.content.Context
import android.os.Handler
import android.support.constraint.ConstraintLayout
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
import com.adinalaptuca.revolutconverter.restmanager.data.Currency

/**
 * Created by adinalaptuca on Aug, 2019
 */
class RatesAdapter() : RecyclerView.Adapter<RatesAdapter.RatesViewHolder>() {
    private var items: ArrayList<Currency> = arrayListOf()
    private var originalItems: ArrayList<Currency> = arrayListOf()
    private lateinit var context: Context
    private var onScrollToTopListener: ScrollToTopListener? = null

    private var text: String = ""

    override fun getItemCount(): Int {
        return items.size
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getItemViewType(position: Int): Int {
        return position
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RatesAdapter.RatesViewHolder {
        context = parent.context
        return RatesViewHolder(LayoutInflater.from(context).inflate(R.layout.item_currency_cell, parent, false))
    }

    fun setList(response: RatesResponse) {
        items.clear()
        items.add(Currency(response.base, 1.toDouble()))

        response.rates.forEach { (key, value) ->
            items.add(Currency(key, value))
        }

        originalItems.clear()
        originalItems.addAll(items)
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: RatesAdapter.RatesViewHolder, position: Int) {

        items[position].let { rate ->
            holder.apply {
                title.text = rate.name
                subtitle.text = if (RatesEnum.getEnumFromValue(rate.name) != null) context.getString(
                    RatesEnum.getEnumFromValue(rate.name)!!.displayValue
                ) else rate.name

                icon.setImageDrawable(context.getDrawable(RatesEnum.getEnumFromValue(rate.name)!!.drawableRes))

                if (position == 0) {
//                    currencyValue.setText(if(text.isNullOrEmpty()) "%.2f".format(rate.value) else text)
                    currencyValue.addTextChangedListener(object : TextWatcher {
                        override fun afterTextChanged(s: Editable?) {
                            text = s.toString()
                            for (i in position + 1..items.size - 1) {
                                if (!s.isNullOrEmpty()) {
                                    Handler().post({ items.set(i, Currency(items[i].name, "%.2f".format(originalItems[i].value * s.toString().toDouble()).toDouble()))
                                    notifyItemChanged(i)})
                                } else {
                                    Handler().post({items.set(i, Currency(items[i].name, "%.2f".format(originalItems[i].value).toDouble())) //if empty string, show original
                                    notifyItemChanged(i)})
                                }
                            }
                        }
                        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
                        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
                    })
                }else{
                    currencyValue.setText(if(text.isNullOrEmpty()) "%.2f".format(rate.value) else "%.2f".format(rate.value * text.toDouble()))
                }

                currencyValue.setSelection(if (!currencyValue.text.isNullOrEmpty()) currencyValue.text!!.length else 0)

                container.setOnClickListener {
//                    items.removeAt(position)
//                    items.add(0, rate)
//
//                    originalItems.clear()
//                    originalItems.addAll(items)
//
//                    notifyDataSetChanged()
//                    notifyItemMoved(position, 0)
                    onScrollToTopListener?.scrollToTop(0, rate.name)
                }
            }
        }
    }

    interface ScrollToTopListener {
        fun scrollToTop(position: Int, base: String)
    }

    fun setScrollToTopListener(listener: ScrollToTopListener) {
        this.onScrollToTopListener = listener
    }

    class RatesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val title: AppCompatTextView = itemView.textTitle
        val subtitle: AppCompatTextView = itemView.textSubtitle
        val currencyValue: AppCompatEditText = itemView.currencyValue
        val icon: AppCompatImageView = itemView.iconFlag
        val container: ConstraintLayout = itemView.containerCell
    }
}