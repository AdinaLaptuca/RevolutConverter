package com.adinalaptuca.revolutconverter.ui.splash

/**
 * Created by adinalaptuca on Aug, 2019
 */

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import com.adinalaptuca.revolutconverter.R
import com.adinalaptuca.revolutconverter.ui.base.BaseActivity
import com.adinalaptuca.revolutconverter.ui.main.MainActivity
import javax.inject.Inject


class SplashActivity : BaseActivity<SplashMvp.Presenter>(), SplashMvp.View {

    @Inject
    lateinit var presenter: SplashMvp.Presenter

    override fun presenter() = presenter

    private val SPLASH_TIME_OUT:Long=2000

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        Handler().postDelayed({
            // This method will be executed once the timer is over
            // Start your app main activity

            startActivity(Intent(this,MainActivity::class.java))

            // close this activity
            finish()
        }, SPLASH_TIME_OUT)

//        presenter().create()
    }

    override fun goToDashboard() {
        startActivity(Intent(this, MainActivity::class.java))
    }

    override fun killActivity(){
        finish()
    }
}