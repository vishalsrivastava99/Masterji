package com.example.Masterji;

public class TutorData {
    private String tutorname, tutoremail, tutormobile, tutordegree, tutorsubjects, tutorexperience, tutorcity,  tutorimage;

    public TutorData(){

    }

    public TutorData(String tutorname, String tutoremail, String tutormobile, String tutordegree, String tutorsubjects, String tutorexperience, String tutorcity, String tutorimage) {
        this.tutorname = tutorname;
        this.tutoremail = tutoremail;
        this.tutormobile = tutormobile;
        this.tutordegree = tutordegree;
        this.tutorsubjects = tutorsubjects;
        this.tutorexperience = tutorexperience;
        this.tutorcity = tutorcity;
        this.tutorimage = tutorimage;
    }

    public String getTutorname() {
        return tutorname;
    }

    public void setTutorname(String tutorname) {
        this.tutorname = tutorname;
    }

    public String getTutoremail() {
        return tutoremail;
    }

    public void setTutoremail(String tutoremail) {
        this.tutoremail = tutoremail;
    }

    public String getTutormobile() {
        return tutormobile;
    }

    public void setTutormobile(String tutormobile) {
        this.tutormobile = tutormobile;
    }

    public String getTutordegree() {
        return tutordegree;
    }

    public void setTutordegree(String tutordegree) {
        this.tutordegree = tutordegree;
    }

    public String getTutorsubjects() {
        return tutorsubjects;
    }

    public void setTutorsubjects(String tutorsubjects) {
        this.tutorsubjects = tutorsubjects;
    }

    public String getTutorexperience() {
        return tutorexperience;
    }

    public void setTutorexperience(String tutorexperience) {
        this.tutorexperience = tutorexperience;
    }

    public String getTutorcity() {
        return tutorcity;
    }

    public void setTutorcity(String tutorcity) {
        this.tutorcity = tutorcity;
    }

    public String getTutorimage() {
        return tutorimage;
    }

    public void setTutorimage(String tutorimage) {
        this.tutorimage = tutorimage;
    }
}