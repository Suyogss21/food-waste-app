package com.example.food_wastage_reduction;

import java.io.Serializable;

public class Mycartmodel implements Serializable {
    String Name;
    String documentId;
String myorder;

    public Mycartmodel() {
    }

    public Mycartmodel(String name, String documentId, String myorder) {
        Name = name;
        this.documentId = documentId;
        this.myorder = myorder;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getDocumentId() {
        return documentId;
    }

    public void setDocumentId(String documentId) {
        this.documentId = documentId;
    }

    public String getMyorder() {
        return myorder;
    }

    public void setMyorder(String myorder) {
        this.myorder = myorder;
    }
}
