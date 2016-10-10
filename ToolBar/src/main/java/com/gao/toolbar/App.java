package com.gao.toolbar;

import android.app.Application;

import com.gao.toolbar.banner.engine.Engine;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class App extends Application {
    private static App sInstance;
    private Engine mEngine;

    @Override
    public void onCreate() {
        super.onCreate();

        sInstance = this;
        mEngine = new Retrofit.Builder()
                .baseUrl("http://7xk9dj.com1.z0.glb.clouddn.com/banner/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build().create(Engine.class);
    }


    public static App getInstance() {
        return sInstance;
    }

    public Engine getEngine() {
        return mEngine;
    }
}