package com.example.actionbartutorial.model;

public class Project {

    String projectname;
    String projectcode;

    public Project(String projectname, String projectcode) {
        this.projectname = projectname;
        this.projectcode = projectcode;
    }

    public String getProjectname() {
        return projectname;
    }

    public String getProjectcode() {
        return projectcode;
    }
}
