package com.template.presentation.di

import com.template.presentation.ui.home.HomeFragment
import com.template.presentation.ui.syndicates.SyndicatesFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Suppress("unused")
@Module
abstract class FragmentBuildersModule {
    @ContributesAndroidInjector()
    abstract fun contributeSyndicatesFragment(): SyndicatesFragment

    @ContributesAndroidInjector()
    abstract fun contributeHomeFragment(): HomeFragment
}


