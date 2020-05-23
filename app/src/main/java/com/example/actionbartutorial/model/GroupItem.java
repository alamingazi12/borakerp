package com.example.actionbartutorial.model;

import android.os.Parcel;
import android.os.Parcelable;

public class GroupItem implements Parcelable {
    public String description;
    public String amount;
    String slno;
    String st;
    String amount1;
    String amount2;
    String amount3;

    protected GroupItem(Parcel in) {
        description = in.readString();
        amount = in.readString();
        slno = in.readString();
        st = in.readString();
        amount1 = in.readString();
        amount2 = in.readString();
        amount3 = in.readString();
        amount4 = in.readString();
        note1 = in.readString();
        note2 = in.readString();
    }

    public static final Creator<GroupItem> CREATOR = new Creator<GroupItem>() {
        @Override
        public GroupItem createFromParcel(Parcel in) {
            return new GroupItem(in);
        }

        @Override
        public GroupItem[] newArray(int size) {
            return new GroupItem[size];
        }
    };

    public String getAmount4() {
        return amount4;
    }

    String amount4;
    String note1;
    String note2;

    public String getSlno() {
        return slno;
    }

    public String getSt() {
        return st;
    }

    public String getAmount1() {
        return amount1;
    }

    public String getAmount2() {
        return amount2;
    }

    public String getAmount3() {
        return amount3;
    }



    public String getNote1() {
        return note1;
    }

    public String getNote2() {
        return note2;
    }






    public GroupItem(String gettNm, String amount, String amount1, String amount2, String amount3, String amount4, String slno, String note1, String note2) {

        this.description=gettNm;
        this.amount=amount;
        this.amount1=amount1;
        this.amount2=amount2;
        this.amount3=amount3;
        this.amount4=amount4;
        this.slno=slno;
        this.note1=note1;
        this.note2=note2;

    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }



    public GroupItem(String description, String amount) {
        this.description = description;
        this.amount = amount;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(description);
        dest.writeString(amount);
        dest.writeString(slno);
        dest.writeString(st);
        dest.writeString(amount1);
        dest.writeString(amount2);
        dest.writeString(amount3);
        dest.writeString(amount4);
        dest.writeString(note1);
        dest.writeString(note2);
    }
}
