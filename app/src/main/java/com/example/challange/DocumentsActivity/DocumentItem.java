package com.example.challange.DocumentsActivity;

public class DocumentItem {
    String fileName, uploadDate, fileContents;

    public DocumentItem(String fileName, String uploadDate, String fileContents){
        this.fileName = fileName;
        this.uploadDate = uploadDate;
        this.fileContents = fileContents;
    }

    public String getDocumentName(){
        return fileName;
    }

    public String getDocumentDate(){
        return uploadDate;
    }

    public String getOcrText(){
        return fileContents;
    }
}