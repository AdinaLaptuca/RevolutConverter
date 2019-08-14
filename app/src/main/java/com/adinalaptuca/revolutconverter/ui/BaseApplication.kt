package com.adinalaptuca.revolutconverter.ui

import android.app.Activity
import android.app.Application
import com.adinalaptuca.revolutconverter.dagger.component.AppComponent
import com.adinalaptuca.revolutconverter.dagger.component.DaggerAppComponent
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import javax.inject.Inject

/**
 * Created by adinalaptuca on Aug, 2019
 */
open class BaseApplication : Application(), HasActivityInjector {

    @Inject
    lateinit var activityDispatchingAndroidInjector: DispatchingAndroidInjector<Activity>

    private var component: AppComponent? = null

    override fun activityInjector() = activityDispatchingAndroidInjector

    override fun onCreate() {

        super.onCreate()

        component = getAppComponent()

        component!!.inject(this)
    }

    protected open fun getAppComponent(): AppComponent {
        return DaggerAppComponent.builder()
            .application(this)
            .build()
    }

    fun component(): AppComponent? {
        return component
    }
}