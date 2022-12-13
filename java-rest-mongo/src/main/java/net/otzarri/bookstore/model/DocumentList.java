package net.otzarri.bookstore.model;

import org.bson.Document;

import java.util.ArrayList;

public class DocumentList {
    private String name;
    private ArrayList<Document> documentList = new ArrayList<>();

    public DocumentList() {
        this.name = "objects";
    }

    public DocumentList(ArrayList<Document> documentList) {
        this.name = "objects";
        this.documentList = documentList;
    }

    public DocumentList(ArrayList<Document> documentList, String name) {
        this.name = name;
        this.documentList = documentList;
    }

    public int size() {
        return this.documentList.size();
    }

    public String toJson() {
        ArrayList<String> jsonDocuments = new ArrayList<>();
        String json = "{}";

        if (this.documentList.size() == 1) {
            json = this.documentList.get(0).toJson();
        } else if (this.documentList.size() > 1) {
            for (Document document : this.documentList) { jsonDocuments.add(document.toJson()); }
            json = String.join(",", jsonDocuments);
            json = String.format("{ \"%s\" : [ %s ] }", this.name, json);
        }
        return json;
    }

    public ArrayList<Document> getDocumentList() {
        return documentList;
    }

    public void setDocumentList(ArrayList<Document> documentList) {
        this.documentList = documentList;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}