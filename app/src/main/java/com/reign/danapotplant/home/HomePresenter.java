package com.reign.danapotplant.home;


import com.reign.danapotplant.models.ProfileResponse;
import com.reign.danapotplant.networking.NetworkError;
import com.reign.danapotplant.networking.Service;

import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

public class HomePresenter {
    private final Service service;
    private final HomeView view;
    private CompositeSubscription subscriptions;

    public HomePresenter(Service service, HomeView view) {
        this.service = service;
        this.view = view;
        this.subscriptions = new CompositeSubscription();
    }

    public void getProfile() {
        view.showWait();

        Subscription subscription = service.getProfile(new Service.GetProfileCallback() {
            @Override
            public void onSuccess(ProfileResponse profileResponse) {
                view.removeWait();
                view.getProfileSuccess(profileResponse);
            }

            @Override
            public void onError(NetworkError networkError) {
                view.removeWait();
                view.onFailure(networkError.getAppErrorMessage());
            }

        });

        subscriptions.add(subscription);
    }

    public void onStop() {
        subscriptions.unsubscribe();
    }
}
