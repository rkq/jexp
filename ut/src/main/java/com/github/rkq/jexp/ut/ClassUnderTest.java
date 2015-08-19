package com.github.rkq.jexp.ut;

/**
 * Created by rick on 8/19/15.
 */
public class ClassUnderTest {
    private Collaborator listener;

    public void setListener(Collaborator listener) {
        this.listener = listener;
    }

    public void addDocument(String title, byte[] document) {
        listener.documentAdded(title);
    }
}
