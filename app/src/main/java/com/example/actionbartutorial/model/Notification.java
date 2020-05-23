package com.example.actionbartutorial.model;

public class Notification {

    String nid;
    String cid;
    String ndate;
    String nhead;

    public Notification(String nid, String cid, String ndate, String nhead, String nbody) {
        this.nid = nid;
        this.cid = cid;
        this.ndate = ndate;
        this.nhead = nhead;
        this.nbody = nbody;
    }

    public String getNid() {
        return nid;
    }

    public String getCid() {
        return cid;
    }

    public String getNdate() {
        return ndate;
    }

    public String getNhead() {
        return nhead;
    }

    public String getNbody() {
        return nbody;
    }

    String nbody;
}
