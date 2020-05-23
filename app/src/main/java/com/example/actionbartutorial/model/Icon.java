package com.example.actionbartutorial.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Icon implements Parcelable {

    String IcoNm;
    String url;
    String CID;

    protected Icon(Parcel in) {
        IcoNm = in.readString();
        url = in.readString();
        CID = in.readString();
        St = in.readString();
        TrnID = in.readString();
        hct = in.readString();
        head1 = in.readString();
        head2 = in.readString();
        head3 = in.readString();
        head4 = in.readString();
        head5 = in.readString();
        head6 = in.readString();
        head7 = in.readString();
        head8 = in.readString();
    }

    public static final Creator<Icon> CREATOR = new Creator<Icon>() {
        @Override
        public Icon createFromParcel(Parcel in) {
            return new Icon(in);
        }

        @Override
        public Icon[] newArray(int size) {
            return new Icon[size];
        }
    };

    public String getCID() {
        return CID;
    }

    public String getSt() {
        return St;
    }

    public String getTrnID() {
        return TrnID;
    }

    public String getHct() {
        return hct;
    }

    public String getHead1() {
        return head1;
    }

    public String getHead2() {
        return head2;
    }

    public String getHead3() {
        return head3;
    }

    public String getHead4() {
        return head4;
    }

    public String getHead5() {
        return head5;
    }

    public String getHead6() {
        return head6;
    }

    public String getHead7() {
        return head7;
    }

    public String getHead8() {
        return head8;
    }

    String St;
    String TrnID;
    String hct;
    String head1;
    String head2;
    String head3;
    String head4;
    String head5;
    String head6;
    String head7;
    String head8;

    public String getIcoNm() {
        return IcoNm;
    }

    public String getUrl() {
        return url;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

        dest.writeString(IcoNm);
        dest.writeString(url);
        dest.writeString(CID);
        dest.writeString(St);
        dest.writeString(TrnID);
        dest.writeString(hct);
        dest.writeString(head1);
        dest.writeString(head2);
        dest.writeString(head3);
        dest.writeString(head4);
        dest.writeString(head5);
        dest.writeString(head6);
        dest.writeString(head7);
        dest.writeString(head8);
    }
}
