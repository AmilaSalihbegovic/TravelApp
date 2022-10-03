package com.example.travelapp.models;

public class MyBucketListModel {

    String DestinationName;
    String DestinationDescription;
    String CurrentTime;
    String CurrentDate;
    String documentId;

    public MyBucketListModel() {
    }


    public MyBucketListModel(String destinationName, String destinationDescription, String currentTime, String currentDate, String documentId) {
        DestinationName = destinationName;
        DestinationDescription = destinationDescription;
        CurrentTime = currentTime;
        CurrentDate = currentDate;
        this.documentId = documentId;
    }

    public String getDocumentId() {
        return documentId;
    }

    public void setDocumentId(String documentId) {
        this.documentId = documentId;
    }

    public String getDestinationName() {
        return DestinationName;
    }

    public void setDestinationName(String destinationName) {
        DestinationName = destinationName;
    }

    public String getDestinationDescription() {
        return DestinationDescription;
    }

    public void setDestinationDescription(String destinationDescription) {
        DestinationDescription = destinationDescription;
    }

    public String getCurrentTime() {
        return CurrentTime;
    }

    public void setCurrentTime(String currentTime) {
        CurrentTime = currentTime;
    }

    public String getCurrentDate() {
        return CurrentDate;
    }

    public void setCurrentDate(String currentDate) {
        CurrentDate = currentDate;
    }
}
