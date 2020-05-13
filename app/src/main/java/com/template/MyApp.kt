package com.template

import android.app.Activity
import android.content.Context
import android.view.WindowManager
import com.template.presentation.di.DaggerAppComponent
import com.template.presentation.util.DisplayMetrics
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.DaggerApplication
import timber.log.Timber
import javax.inject.Inject

class MyApp : DaggerApplication() {
    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return DaggerAppComponent.builder().application(this).build()
    }

    @Inject // AndroidInjection.inject() gets a DispatchingAndroidInjector<Activity>
    // from the Application and passes your activity to inject(Activity).
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Activity>

    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
//        AppInjector.init(this)
        DisplayMetrics.setMetrics(getSystemService(Context.WINDOW_SERVICE) as WindowManager)
    }

//    override fun activityInjector() = dispatchingAndroidInjector
}