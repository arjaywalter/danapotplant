package com.reign.danapotplant.networking;


import com.reign.danapotplant.models.ProfileResponse;

import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

public class Service {
    private final NetworkService networkService;

    public Service(NetworkService networkService) {
        this.networkService = networkService;
    }

    public Subscription getProfile(final GetProfileCallback callback) {

        return networkService.getProfile()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .onErrorResumeNext(new Func1<Throwable, Observable<? extends ProfileResponse>>() {
                    @Override
                    public Observable<? extends ProfileResponse> call(Throwable throwable) {
                        return Observable.error(throwable);
                    }
                })
                .subscribe(new Subscriber<ProfileResponse>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        callback.onError(new NetworkError(e));

                    }

                    @Override
                    public void onNext(ProfileResponse profileResponse) {
                        callback.onSuccess(profileResponse);

                    }
                });
    }

    public interface GetProfileCallback {
        void onSuccess(ProfileResponse profileResponse);

        void onError(NetworkError networkError);
    }
}
