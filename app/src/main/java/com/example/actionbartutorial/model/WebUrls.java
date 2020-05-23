package com.example.actionbartutorial.model;

import android.os.Parcel;
import android.os.Parcelable;

public class WebUrls implements Parcelable {

        String  IcoID;
        String  IcoNm;
        String  IcoNm1;
        String  St;
        String url;

    public WebUrls(String icoID, String icoNm, String icoNm1, String st, String url) {
        IcoID = icoID;
        IcoNm = icoNm;
        IcoNm1 = icoNm1;
        St = st;
        this.url = url;
    }

    protected WebUrls(Parcel in) {
        IcoID = in.readString();
        IcoNm = in.readString();
        IcoNm1 = in.readString();
        St = in.readString();
        url = in.readString();
    }

    public static final Creator<WebUrls> CREATOR = new Creator<WebUrls>() {
        @Override
        public WebUrls createFromParcel(Parcel in) {
            return new WebUrls(in);
        }

        @Override
        public WebUrls[] newArray(int size) {
            return new WebUrls[size];
        }
    };

    public String getIcoID() {
        return IcoID;
    }

    public String getIcoNm() {
        return IcoNm;
    }

    public String getIcoNm1() {
        return IcoNm1;
    }

    public String getSt() {
        return St;
    }

    public String getUrl() {
        return url;
    }

    public void setIcoID(String icoID) {
        IcoID = icoID;
    }

    public void setIcoNm(String icoNm) {
        IcoNm = icoNm;
    }

    public void setIcoNm1(String icoNm1) {
        IcoNm1 = icoNm1;
    }

    public void setSt(String st) {
        St = st;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(IcoID);
        dest.writeString(IcoNm);
        dest.writeString(IcoNm1);
        dest.writeString(St);
        dest.writeString(url);
    }
}
