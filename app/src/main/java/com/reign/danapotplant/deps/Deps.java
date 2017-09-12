package com.reign.danapotplant.deps;


import com.reign.danapotplant.home.HomeActivity;
import com.reign.danapotplant.networking.NetworkModule;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {NetworkModule.class})
public interface Deps {
    void inject(HomeActivity homeActivity);
}
