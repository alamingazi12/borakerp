package com.example.actionbartutorial.model;

public class Offer {

    public String oid;
    public String ohead;
    public String obody;

    public String ostdate;
    public String oenddate;
    public String ostutus;

    public Offer(String oid, String ohead, String obody, String ostdate, String oenddate, String ostutus) {
        this.oid = oid;
        this.ohead = ohead;
        this.obody = obody;
        this.ostdate = ostdate;
        this.oenddate = oenddate;
        this.ostutus = ostutus;
    }

    public String getOid() {
        return oid;
    }

    public String getOhead() {
        return ohead;
    }

    public String getObody() {
        return obody;
    }

    public String getOstdate() {
        return ostdate;
    }

    public String getOenddate() {
        return oenddate;
    }

    public String getOstutus() {
        return ostutus;
    }
}
