
package com.reign.danapotplant.models;

import javax.annotation.Generated;
import com.google.gson.annotations.SerializedName;

@Generated("net.hexar.json2pojo")
@SuppressWarnings("unused")
public class Exam {

    @SerializedName("name")
    private String mName;
    @SerializedName("progress")
    private Long mProgress;

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public Long getProgress() {
        return mProgress;
    }

    public void setProgress(Long progress) {
        mProgress = progress;
    }

}
