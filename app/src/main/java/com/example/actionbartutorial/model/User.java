package com.example.actionbartutorial.model;

import android.os.Parcel;
import android.os.Parcelable;

public class User implements Parcelable {

  //  public static final String FID = ;
    String cid;
    String cNm;
    String uNm;
    String uPs;
    String uSt;
    String url;
    String tblNm;
    String FID;
    String address;
    String email;
    String phone;
    String dob;

    public String getFID() {
        return FID;
    }

    public void setFID(String FID) {
        this.FID = FID;
    }



    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getTblNm() {
        return tblNm;
    }

    public void setTblNm(String tblNm) {
        this.tblNm = tblNm;
    }

    public User(String cid, String cNm, String uNm, String uPs, String iSt, String address, String email, String phone, String dob, String url, String tblNm,String FID) {
        this.cid = cid;
        this.cNm = cNm;
        this.uNm = uNm;
        this.uPs = uPs;
        this.uSt = iSt;
        this.address = address;
        this.email = email;
        this.phone = phone;
        this.dob = dob;
        this.url=url;
        this.tblNm=tblNm;
        this.FID=FID;
    }



    protected User(Parcel in) {
        cid = in.readString();
        cNm = in.readString();
        uNm = in.readString();
        uPs = in.readString();
        uSt = in.readString();
        address = in.readString();
        email = in.readString();
        phone = in.readString();
        dob = in.readString();
        url=in.readString();
        tblNm=in.readString();
        FID=in.readString();
    }

    public static final Creator<User> CREATOR = new Creator<User>() {
        @Override
        public User createFromParcel(Parcel in) {
            return new User(in);
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };

    public String getCid() {
        return cid;
    }

    public String getcNm() {
        return cNm;
    }

    public String getuNm() {
        return uNm;
    }

    public String getuPs() {
        return uPs;
    }

    public String getuSt() {
        return uSt;
    }

    public String getAddress() {
        return address;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public String getDob() {
        return dob;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(cid);
        dest.writeString(cNm);
        dest.writeString(uNm);
        dest.writeString(uPs);
        dest.writeString(uSt);
        dest.writeString(address);
        dest.writeString(email);
        dest.writeString(phone);
        dest.writeString(dob);
        dest.writeString(url);
        dest.writeString(tblNm);
        dest.writeString(FID);

    }
}
