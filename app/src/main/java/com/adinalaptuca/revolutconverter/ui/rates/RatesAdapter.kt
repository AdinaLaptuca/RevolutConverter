package com.adinalaptuca.revolutconverter.ui.rates

import android.content.Context
import android.graphics.PorterDuff
import android.os.Handler
import android.support.constraint.ConstraintLayout
import android.support.v4.content.ContextCompat
import android.support.v7.widget.AppCompatEditText
import android.support.v7.widget.AppCompatImageView
import android.support.v7.widget.AppCompatTextView
import android.support.v7.widget.RecyclerView
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import com.adinalaptuca.revolutconverter.R
import com.adinalaptuca.revolutconverter.restmanager.data.RatesResponse
import kotlinx.android.synthetic.main.item_currency_cell.view.*
import com.adinalaptuca.revolutconverter.restmanager.data.Currency
import com.adinalaptuca.revolutconverter.ui.main.MainActivity

/**
 * Created by adinalaptuca on Aug, 2019
 */
class RatesAdapter() : RecyclerView.Adapter<RatesAdapter.RatesViewHolder>() {
    private var items: ArrayList<Currency> = arrayListOf()
    private var originalItems: ArrayList<Currency> = arrayListOf()
    private lateinit var context: Context
    private var onScrollToTopListener: ScrollToTopListener? = null
    private var onHideKeyboardListener: HideKeyboardListener? = null

    private var text: String = ""

    override fun getItemCount(): Int {
        return items.size
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getItemViewType(position: Int): Int {
        return position // I basically destroy the recycling :( otherwise I get an ugly bug on scrolling
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
                                    items.set(i, Currency(items[i].name, "%.2f".format(originalItems[i].value * s.toString().toDouble()).toDouble()))
//                                    Handler().post({notifyItemChanged(i)})
                                } else {
                                    items.set(i, Currency(items[i].name, "%.2f".format(originalItems[i].value).toDouble())) //if empty string, show original value
//                                    Handler().post({notifyItemChanged(i)})
                                }
                            }
                        }
                        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
                        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
                    })
                }else{
                    currencyValue.apply{
                        isEnabled = false
                        setTextColor(ContextCompat.getColor(context!!, android.R.color.black))
//                        background.mutate().setColorFilter(ContextCompat.getColor(context!!, android.R.color.black), PorterDuff.Mode.SRC_ATOP);
                    }
                    currencyValue.setText(if(text.isNullOrEmpty()) "%.2f".format(rate.value) else "%.2f".format(rate.value * text.toDouble()))

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

                currencyValue.setSelection(if (!currencyValue.text.isNullOrEmpty()) currencyValue.text!!.length else 0)

                currencyValue.setOnEditorActionListener { _, actionId, _ ->
                    if (actionId == EditorInfo.IME_ACTION_DONE) {
                        currencyValue.clearFocus()
                        onHideKeyboardListener?.doHideKeyboard()
                    }
                    false
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

    interface HideKeyboardListener {
        fun doHideKeyboard()
    }

    fun setHideKeyboardListener(listener: HideKeyboardListener) {
        this.onHideKeyboardListener = listener
    }

    class RatesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val title: AppCompatTextView = itemView.textTitle
        val subtitle: AppCompatTextView = itemView.textSubtitle
        val currencyValue: AppCompatEditText = itemView.currencyValue
        val icon: AppCompatImageView = itemView.iconFlag
        val container: ConstraintLayout = itemView.containerCell
    }
}