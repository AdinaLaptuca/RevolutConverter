package com.adinalaptuca.revolutconverter.ui.base

import com.adinalaptuca.revolutconverter.data.networkmanager.NetworkManager
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

/**
 * Created by adinalaptuca on Aug, 2019
 */
abstract class BasePresenter<V : BaseMvp.View> constructor(private var networkManager: NetworkManager) :
    BaseMvp.Presenter {

    protected var view: V? = null
    override var hasInternetConnection: Boolean = networkManager.hasNetworkConnection
    protected var subscription = CompositeDisposable()
    protected var subjectsSubscription = CompositeDisposable()

    init {
        addNetworkSubscription()
    }

    @Suppress("UNCHECKED_CAST")
    override fun attach(view: BaseMvp.View) {
        addNetworkSubscription()
        this.view = view as V
    }

    /** Subscribe to Internet Connection Publishes **/
    private fun addNetworkSubscription() {
        subscription.add(
            networkManager.onlineSubject
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe { hasInternet ->
                    hasInternetConnection = hasInternet
                }
        )
    }

    /** Set the initial value of the internet connection
     * The changes that will apply after will be handled by the network subscription **/
    override fun setInitialInternetConnectionValue(hasInternet: Boolean) {
        hasInternetConnection = hasInternet
    }

    /** On Fragment detach delete view and clear all subscriptions **/
    override fun detach() {
        view = null
        subscription.clear()
        subjectsSubscription.clear()
    }
}