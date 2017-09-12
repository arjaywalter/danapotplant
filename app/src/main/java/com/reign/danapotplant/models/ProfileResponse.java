
package com.reign.danapotplant.models;

import java.util.List;
import javax.annotation.Generated;
import com.google.gson.annotations.SerializedName;

@Generated("net.hexar.json2pojo")
@SuppressWarnings("unused")
public class ProfileResponse {

    @SerializedName("attachments")
    private Object mAttachments;
    @SerializedName("avatar")
    private String mAvatar;
    @SerializedName("bidperiod")
    private Long mBidperiod;
    @SerializedName("exams")
    private List<Exam> mExams;
    @SerializedName("files")
    private Object mFiles;
    @SerializedName("skills")
    private List<Skill> mSkills;
    @SerializedName("username")
    private String mUsername;

    public Object getAttachments() {
        return mAttachments;
    }

    public void setAttachments(Object attachments) {
        mAttachments = attachments;
    }

    public String getAvatar() {
        return mAvatar;
    }

    public void setAvatar(String avatar) {
        mAvatar = avatar;
    }

    public Long getBidperiod() {
        return mBidperiod;
    }

    public void setBidperiod(Long bidperiod) {
        mBidperiod = bidperiod;
    }

    public List<Exam> getExams() {
        return mExams;
    }

    public void setExams(List<Exam> exams) {
        mExams = exams;
    }

    public Object getFiles() {
        return mFiles;
    }

    public void setFiles(Object files) {
        mFiles = files;
    }

    public List<Skill> getSkills() {
        return mSkills;
    }

    public void setSkills(List<Skill> skills) {
        mSkills = skills;
    }

    public String getUsername() {
        return mUsername;
    }

    public void setUsername(String username) {
        mUsername = username;
    }

}
