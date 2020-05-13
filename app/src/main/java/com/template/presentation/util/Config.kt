package com.template.presentation.util

import android.content.Context
import android.os.Build
import android.os.LocaleList
import java.util.*

class Config {
    companion object {
        val LANGUAGE: String = "ar"
        val API_KEY: String = "62517db39eef319ca9749d78ffa28fac9b54307fddc1c60dcb532a6514a7b5b1"
    }

    class ContextWrapper(base: Context) : android.content.ContextWrapper(base) {

        companion object {
            fun wrap(context: Context, newLocale: Locale): ContextWrapper {
                var context = context

                val res = context.resources
                val configuration = res.configuration

                when {
                    Build.VERSION.SDK_INT >= 24 -> {
                        configuration.setLocale(newLocale)

                        val localeList = LocaleList(newLocale)
                        LocaleList.setDefault(localeList)
                        configuration.locales = localeList

                        context = context.createConfigurationContext(configuration)
                    }
                    Build.VERSION.SDK_INT >= 17 -> {
                        configuration.setLocale(newLocale)
                        context = context.createConfigurationContext(configuration)
                    }
                    else -> {
                        configuration.locale = newLocale
                        res.updateConfiguration(configuration, res.displayMetrics)
                    }
                }

                return ContextWrapper(context)
            }
        }
    }
}