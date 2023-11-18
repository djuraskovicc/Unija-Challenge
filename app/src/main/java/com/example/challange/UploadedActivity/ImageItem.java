package com.example.challange.UploadedActivity;

public class ImageItem {
    String imageName;
    String imageUrl;

    public ImageItem(String imageUrl, String imageName){
        this.imageUrl = imageUrl;
        this.imageName = imageName;
    }

    public String getImageUrl(){
        return imageUrl;
    }

    public String getDocumentName(){
        return imageName;
    }
}