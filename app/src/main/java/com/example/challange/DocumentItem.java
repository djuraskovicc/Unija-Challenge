package com.example.challange;

public class DocumentItem {
    String documentName, date;

    public DocumentItem(String documentName, String date){
        this.documentName = documentName;
        this.date = date;
    }

    public String getDocumentName(){
        return documentName;
    }

    public String getDocumentDate(){
        return date;
    }
}