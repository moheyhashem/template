package com.template.presentation.di

import com.template.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

// It extends the AndroidInjector to perform injection on a concrete Android component type (in our case MainActivity)
@Suppress("unused")
@Module
abstract class MainActivityModule {
    @ContributesAndroidInjector(modules = [FragmentBuildersModule::class])
    abstract fun contributeMainActivity(): MainActivity
}
