package com.lambdaschool.datapersistencesprintchallenge

import android.app.Application
import com.lambdaschool.datapersistencesprintchallenge.database.FavoritesDBBuilder
import timber.log.Timber

class App : Application() {

    class MyDebugTree : Timber.DebugTree() {
        override fun createStackElementTag(element: StackTraceElement): String? {
            return String.format(
                "[C:%s] [M:%s] [L:%s]",
                super.createStackElementTag(element),
                element.methodName,
                element.lineNumber
            )
        }
    }

    companion object {
        var favoritesDBBuilder: FavoritesDBBuilder? = null
    }

    override fun onCreate() {
        super.onCreate()

        favoritesDBBuilder = FavoritesDBBuilder(applicationContext)

        // "Timber" Library
        if (BuildConfig.DEBUG) {
            Timber.plant(MyDebugTree())
        }
    }
}