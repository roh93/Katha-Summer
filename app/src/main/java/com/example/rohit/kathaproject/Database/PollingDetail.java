package com.example.rohit.kathaproject.Database;

/**
 * Created by Rohit on 22-06-2017.
 */

public class PollingDetail {

    private String sex;
    private int age;
    private String occupation;
    private String issueName;


    public PollingDetail(String sex, int age, String occupation, String issueName) {
        this.sex = sex;
        this.age = age;
        this.occupation = occupation;
        this.issueName = issueName;
    }

    String getSex() {
        return sex;
    }


    int getAge() {
        return age;
    }


    String getOccupation() {
        return occupation;
    }


    String getIssueName() {
        return issueName;
    }

}
