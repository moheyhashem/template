package com.template.presentation.di

import android.app.Application
import com.template.MyApp
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import javax.inject.Singleton

// responsible for injecting the Application class
@Singleton // Dagger doesn’t allow for unscoped components to refer to scoped bindings
@Component(
        modules = [
            AndroidInjectionModule::class, // needed to ensure the binding of the Android base types (Activities, Fragments, etc.)
            AppModule::class,
            MainActivityModule::class,
            DatabaseModule::class,
            NetworkModule::class,
            RepositoryModule::class,
            UseCasesModule::class]
)
interface AppComponent : AndroidInjector<MyApp> { // extends AndroidInjector<MyApplication>
    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }
    // allow to inject into our App
//    fun inject(myApp: MyApp)
}