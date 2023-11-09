package com.example.challange;

public class GmailItem {
    String email = "You have a new Mail !";
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