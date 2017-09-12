package com.reign.danapotplant.home;


import com.reign.danapotplant.models.ProfileResponse;

public interface HomeView {
    void showWait();

    void removeWait();

    void onFailure(String appErrorMessage);

    void getProfileSuccess(ProfileResponse profileResponse);

}
