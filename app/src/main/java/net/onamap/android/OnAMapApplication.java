package net.onamap.android;

import android.app.Application;

import com.jakewharton.picasso.OkHttp3Downloader;
import com.squareup.picasso.Picasso;

import net.onamap.android.modules.AppModule;
import net.onamap.android.modules.DaggerNetComponent;
import net.onamap.android.modules.NetComponent;
import net.onamap.android.modules.NetModule;

import okhttp3.OkHttpClient;
import timber.log.Timber;

public class OnAMapApplication extends Application {


    private NetComponent mNetComponent;
    public static Picasso picasso;

    @Override
    public void onCreate() {
        super.onCreate();
//        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
//                .setDefaultFontPath("fonts/Roboto-ThinItalic.ttf")
//                .setFontAttrId(R.attr.fontPath)
//                .build()
//        );

        if (BuildConfig.DEBUG) {
            Timber.plant(new Timber.DebugTree());
        }


        // Dagger%COMPONENT_NAME%
        mNetComponent = DaggerNetComponent.builder()
                // list of modules that are part of this component need to be created here too
                .appModule(new AppModule(this)) // This also corresponds to the name of your module: %component_name%Module
                .netModule(new NetModule("https://api.github.com"))
                .build();

        // If a Dagger 2 component does not have any constructor arguments for any of its modules,
        // then we can use .create() as a shortcut instead:
        //  mNetComponent = com.codepath.dagger.components.DaggerNetComponent.create();


        OkHttpClient client = new OkHttpClient.Builder().build();
        picasso = new Picasso.Builder(getApplicationContext())
                .downloader(new OkHttp3Downloader(client))
                .build();

    }


    public NetComponent getNetComponent() {
        return mNetComponent;
    }
}
