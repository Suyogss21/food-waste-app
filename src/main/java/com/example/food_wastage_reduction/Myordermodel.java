package com.example.food_wastage_reduction;

public class Myordermodel {
    String Name;
    String documentId;

    public Myordermodel() {
    }

    public Myordermodel(String name, String documentId) {
        this.Name = name;
        this.documentId = documentId;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        this.Name = name;
    }

    public String getDocumentId() {
        return documentId;
    }

    public void setDocumentId(String documentId) {
        this.documentId = documentId;
    }
}
