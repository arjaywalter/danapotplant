package com.reign.danapotplant.networking;


import com.reign.danapotplant.models.ProfileResponse;

import retrofit2.http.GET;
import rx.Observable;

public interface NetworkService {

    @GET("api/danapotplant")
    Observable<ProfileResponse> getProfile();

}
