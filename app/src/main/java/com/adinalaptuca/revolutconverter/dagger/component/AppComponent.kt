package com.adinalaptuca.revolutconverter.dagger.component

import android.app.Application
import com.adinalaptuca.revolutconverter.dagger.builder.ActivityBuilder
import com.adinalaptuca.revolutconverter.dagger.module.AppModule
import com.adinalaptuca.revolutconverter.data.DataModule
import com.adinalaptuca.revolutconverter.ui.BaseApplication
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

/**
 * Created by adinalaptuca on Aug, 2019
 */

@Singleton
@Component(modules = [(AndroidSupportInjectionModule::class), (AppModule::class), (ActivityBuilder::class), (DataModule::class)])
interface AppComponent {

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }

    fun inject(app: BaseApplication)
}