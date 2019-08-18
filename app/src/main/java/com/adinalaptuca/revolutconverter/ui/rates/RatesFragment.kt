package com.adinalaptuca.revolutconverter.ui.rates

import android.content.Context
import android.os.Bundle
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.adinalaptuca.revolutconverter.R
import com.adinalaptuca.revolutconverter.restmanager.data.RatesResponse
import com.adinalaptuca.revolutconverter.ui.base.BaseFragment
import com.adinalaptuca.revolutconverter.ui.main.MainActivity
import com.adinalaptuca.revolutconverter.utils.Keyboard.hideKeyboard
import kotlinx.android.synthetic.main.fragment_rates.*
import javax.inject.Inject

/**
 * Created by adinalaptuca on Aug, 2019
 */
class RatesFragment : BaseFragment<RatesMvp.Presenter>(), SwipeRefreshLayout.OnRefreshListener, RatesMvp.View {

    @Inject
    lateinit var presenter: RatesMvp.Presenter

    @Inject
    lateinit var ratesAdapter: RatesAdapter

    override fun presenter() = presenter

    private var activityContext: Context? = null

    private lateinit var rates: HashMap<String, Double>

    companion object {
        fun newInstance(): RatesFragment {
            return RatesFragment()
        }
    }

    override fun layout(): Int = R.layout.fragment_rates

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        activityContext = context
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        ratesAdapter.setHasStableIds(true);

        recyclerCurrencies.apply {
            layoutManager = LinearLayoutManager(activity)
            adapter = ratesAdapter
        }

        swipe_container.setOnRefreshListener(this)

        doIfHasInternetConnectivity{
            presenter.getRates("EUR")
        }

        ratesAdapter.setScrollToTopListener(object : RatesAdapter.ScrollToTopListener {
            override fun scrollToTop(position: Int, base:String) {
//                doIfHasInternetConnectivity {
                    recyclerCurrencies.smoothScrollToPosition(position)
                    presenter.changeRate(base)
//                }
            }
        })

        ratesAdapter.setHideKeyboardListener(object : RatesAdapter.HideKeyboardListener {
            override fun doHideKeyboard() {
                hideKeyboard(activityContext as MainActivity)
            }
        })
    }

    override fun showLoading(show: Boolean) {
        if (show){
            loadingIcon.visibility = View.VISIBLE
            showErrorMessage(false)
            recyclerCurrencies.visibility = View.GONE
        }
        else {
            swipe_container.isRefreshing = false
            loadingIcon.visibility = View.GONE
            recyclerCurrencies.visibility = View.VISIBLE
        }
    }

    override fun showErrorMessage(show: Boolean) {
        if (show) {
            swipe_container.isRefreshing = false
            errorMessage.visibility = View.VISIBLE
            recyclerCurrencies.visibility = View.GONE
        }
        else
            errorMessage.visibility = View.GONE
    }

    override fun onRefresh() {
//        doIfHasInternetConnectivity {
            swipe_container.isRefreshing = true
            showLoading(false)
            presenter.changeRate("EUR") // come back to original
//        }
    }

    override fun setRatesList(response: RatesResponse) {
        recyclerCurrencies.visibility = View.VISIBLE
        swipe_container.isRefreshing = false
        ratesAdapter.setList(response)
    }
}
