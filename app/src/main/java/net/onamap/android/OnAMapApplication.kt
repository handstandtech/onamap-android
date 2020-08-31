package net.onamap.android

import android.app.Application
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import net.onamap.android.dao.PhotoDao
import timber.log.Timber
import timber.log.Timber.DebugTree

class OnAMapApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) {
            Timber.plant(DebugTree())
        }

        CoroutineScope(Dispatchers.Default).launch {
            PhotoDao.init(applicationContext = applicationContext)
        }
    }
}