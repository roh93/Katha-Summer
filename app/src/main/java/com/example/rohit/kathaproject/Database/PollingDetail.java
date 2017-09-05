package com.example.rohit.kathaproject.Database;

/**
 * Created by Rohit on 22-06-2017.
 */

public class PollingDetail {

    private String sex;
    private int age;
    private String occupation;
    private String issueName1;
    private String issueName2;
    private String issueName3;


    public PollingDetail(String sex, int age, String occupation, String issueName1,String issueName2, String issueName3) {
        this.sex = sex;
        this.age = age;
        this.occupation = occupation;
        this.issueName1 = issueName1;
        this.issueName2 = issueName2;
        this.issueName3 = issueName3;
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


    String getIssueName1() {
        return issueName1;
    }

    String getIssueName2() {
        return issueName2;
    }

    String getIssueName3() {
        return issueName3;
    }

}
