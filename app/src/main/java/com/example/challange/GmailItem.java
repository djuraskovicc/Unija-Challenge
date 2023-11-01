package com.example.challange;

public class GmailItem {
    String email, emailAddress;

    public GmailItem(String email, String emailAddress){
        this.email = email;
        this.emailAddress = emailAddress;
    }

    public String getTitle(){
        return email;
    }

    public String getEmailAddress(){
        return emailAddress;
    }

}