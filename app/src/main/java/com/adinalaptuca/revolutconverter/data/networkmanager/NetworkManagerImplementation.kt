package com.adinalaptuca.revolutconverter.data.networkmanager

import android.app.NotificationManager
import android.content.Context
import android.net.ConnectivityManager
import io.reactivex.subjects.BehaviorSubject

/**
 * Created by adinalaptuca on Aug, 2019
 */

internal class NetworkManagerImplementation(val context: Context) : NetworkManager {

    override var hasNetworkConnection: Boolean = false
    private val nativeNotificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

    override val onlineSubject: BehaviorSubject<Boolean> = BehaviorSubject.createDefault(hasInternet())

    private fun hasInternet(): Boolean {
        val connection = (context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager)
        val hasInternet = connection.activeNetworkInfo != null && connection.activeNetworkInfo.isConnectedOrConnecting
        hasNetworkConnection = hasInternet
        return hasInternet
    }

    override fun publish(isConnected: Boolean) {
        hasNetworkConnection = isConnected
        onlineSubject.onNext(isConnected)
    }

    override fun clearAllNotificationsAtLogout() {
        nativeNotificationManager.cancelAll()
    }
}