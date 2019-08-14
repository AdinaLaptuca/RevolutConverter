package com.adinalaptuca.revolutconverter.ui.main

import android.os.Bundle
import android.support.v4.app.Fragment
import com.adinalaptuca.revolutconverter.R
import com.adinalaptuca.revolutconverter.ui.base.BaseActivity
import com.adinalaptuca.revolutconverter.ui.base.BaseFragment
import com.adinalaptuca.revolutconverter.ui.rates.RatesFragment
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.HasSupportFragmentInjector
import javax.inject.Inject

class MainActivity : BaseActivity<MainMvp.Presenter>(), HasSupportFragmentInjector, MainMvp.View {

    @Inject
    internal lateinit var fragmentDispatchingAndroidInjector: DispatchingAndroidInjector<Fragment>

    override fun supportFragmentInjector(): AndroidInjector<Fragment> = fragmentDispatchingAndroidInjector

    @Inject
    internal lateinit var presenter: MainMvp.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        presenter.create()

        if (savedInstanceState != null) {
            //Restore the fragment's instance
            val currentFragment = supportFragmentManager.getFragment(savedInstanceState, "currentFragment")
            if (currentFragment != null) {
                openFragment(currentFragment, "myFragmentName", false)
            } else {
                openFragment(RatesFragment.newInstance(), RatesFragment::class.java.name, false)
            }
        } else {
            openFragment(RatesFragment.newInstance(), RatesFragment::class.java.name, false)
        }
    }

    fun openFragment(
        fragment: Fragment, tag: String, addToBackstack: Boolean = true, popPrevious: Boolean = false,
        animate: Boolean = true, animateOnlyEnter: Boolean = false
    ) {
        val ft = supportFragmentManager.beginTransaction()

        if (animateOnlyEnter) {
            ft.setCustomAnimations(R.animator.fade_in, R.animator.fade_out)
        } else if (animate) {
            ft.setCustomAnimations(
                R.animator.fade_in, R.animator.fade_out,
                R.animator.fade_in, R.animator.fade_out
            )
        }

        if (addToBackstack) {
            ft.addToBackStack(tag)
        }

        if (popPrevious) supportFragmentManager.popBackStack()

        if (arrayListOf(RatesFragment::class.java.name).contains(tag)) {
            for (i in 0 until supportFragmentManager.backStackEntryCount) {
                supportFragmentManager.popBackStackImmediate()
            }
        }

        ft.replace(R.id.fragment_content, fragment)
        ft.commit()
    }


    fun popFragment() {
        supportFragmentManager.popBackStack()
    }

    override fun presenter(): MainMvp.Presenter = presenter


    override fun onBackPressed() {
        try {

            val displayedFragment = supportFragmentManager.findFragmentById(R.id.fragment_content)

            if (displayedFragment != null && (displayedFragment as? BaseFragment<*>)?.isOverridingBack == true) {
                (displayedFragment as? BaseFragment<*>)?.onBackTriggered()
            } else {
                try {
                    super.onBackPressed()
                } catch (exception: Exception) {
                }
            }
        } catch (exception: java.lang.Exception) {
            try {
                super.onBackPressed()
            } catch (exception: Exception) {
            }
        }
    }
}
