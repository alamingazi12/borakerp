package com.example.actionbartutorial.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Version implements Parcelable {


    String vID1;
    String vID2;
    String vDate;
    String fburl;
    String yturl;
    String insurl;
    String twurl;
    String updateurl;
    String autono;

    public Version(String vID1, String vID2, String vDate, String fburl, String yturl, String insurl, String twurl, String updateurl, String autono) {
        this.vID1 = vID1;
        this.vID2 = vID2;
        this.vDate = vDate;
        this.fburl = fburl;
        this.yturl = yturl;
        this.insurl = insurl;
        this.twurl = twurl;
        this.updateurl = updateurl;
        this.autono = autono;
    }

    protected Version(Parcel in) {
        vID1 = in.readString();
        vID2 = in.readString();
        vDate = in.readString();
        fburl = in.readString();
        yturl = in.readString();
        insurl = in.readString();
        twurl = in.readString();
        updateurl = in.readString();
        autono = in.readString();
    }

    public static final Creator<Version> CREATOR = new Creator<Version>() {
        @Override
        public Version createFromParcel(Parcel in) {
            return new Version(in);
        }

        @Override
        public Version[] newArray(int size) {
            return new Version[size];
        }
    };

    public String getvID1() {
        return vID1;
    }

    public String getvID2() {
        return vID2;
    }

    public String getvDate() {
        return vDate;
    }

    public String getFburl() {
        return fburl;
    }

    public String getYturl() {
        return yturl;
    }

    public String getInsurl() {
        return insurl;
    }

    public String getTwurl() {
        return twurl;
    }

    public String getUpdateurl() {
        return updateurl;
    }

    public String getAutono() {
        return autono;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(vID1);
        dest.writeString(vID2);
        dest.writeString(vDate);
        dest.writeString(fburl);
        dest.writeString(yturl);
        dest.writeString(insurl);
        dest.writeString(twurl);
        dest.writeString(updateurl);
        dest.writeString(autono);
    }
}
