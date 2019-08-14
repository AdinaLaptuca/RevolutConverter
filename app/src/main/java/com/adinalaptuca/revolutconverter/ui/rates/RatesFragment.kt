package com.adinalaptuca.revolutconverter.ui.rates

import android.content.Context
import android.os.Bundle
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.adinalaptuca.revolutconverter.R
import com.adinalaptuca.revolutconverter.restmanager.data.RatesResponse
import com.adinalaptuca.revolutconverter.ui.base.BaseFragment
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

        recyclerCurrencies.apply {
            layoutManager = LinearLayoutManager(activity)
            adapter = ratesAdapter
        }

        presenter.getRates("EUR")
    }

    override fun onRefresh() {
        swipe_container.isRefreshing = false
    }

    override fun setRatesList(response: RatesResponse) {
        ratesAdapter.setList(response)
    }
}
