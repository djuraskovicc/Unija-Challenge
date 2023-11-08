package com.example.challange;

public class DocumentItem {
    String documentName = "Document name";
    String date = "1/1/2000";

    /*public DocumentItem(String documentName, String date){
        this.documentName = documentName;
        this.date = date;
    }*/

    public String getDocumentName(){
        return documentName;
    }

    public String getDocumentDate(){
        return date;
    }
}