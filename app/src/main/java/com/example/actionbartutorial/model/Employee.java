package com.example.actionbartutorial.model;

public class Employee {



    public String name;
    public int address;
    public String productname;

    public String getProductname() {
        return productname;
    }

    public void setProductname(String productname) {
        this.productname = productname;
    }



    public Employee(String name, int address,String productname) {
        this.name = name;
        this.productname=productname;
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public int getAddress() {
        return address;
    }
}
