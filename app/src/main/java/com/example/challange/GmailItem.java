package com.example.challange;

public class GmailItem {
    String email = "Gmail";
    String emailAddress;

    public GmailItem(String emailAddress){
        this.emailAddress = emailAddress;
    }

    public String getTitle(){
        return email;
    }

    public String getEmailAddress(){
        return emailAddress;
    }

}