package net.onamap.android

import android.app.Application
import net.onamap.android.dao.PhotoDao
import timber.log.BuildConfig
import timber.log.Timber
import timber.log.Timber.DebugTree

class OnAMapApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        PhotoDao.init(applicationContext = applicationContext)
        if (BuildConfig.DEBUG) {
            Timber.plant(DebugTree())
        }

    }
}