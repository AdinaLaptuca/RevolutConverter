package com.adinalaptuca.revolutconverter.data.networkmanager

import io.reactivex.subjects.BehaviorSubject

/**
 * Created by adinalaptuca on Aug, 2019
 */
interface NetworkManager {
    var hasNetworkConnection : Boolean
    val onlineSubject : BehaviorSubject<Boolean>
    fun publish(isConnected: Boolean)
    fun clearAllNotificationsAtLogout()
}