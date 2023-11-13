package com.example.challange;

public class DocumentItem {
    String documentName, date, ocrText;

    public DocumentItem(String documentName, String date, String ocrText){
        this.documentName = documentName;
        this.date = date;
        this.ocrText = ocrText;
    }

    public String getDocumentName(){
        return documentName;
    }

    public String getDocumentDate(){
        return date;
    }

    public String getOcrText(){
        return ocrText;
    }
}