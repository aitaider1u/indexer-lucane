package org.example.indexer;

import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.StringField;
import org.apache.lucene.document.TextField;

import java.util.ArrayList;
import java.util.List;

public class DocumentFactory {

    private static Document createUserDocument(Integer id, String firstName, String lastName, String website)
    {
        Document document = new Document();
        document.add(new StringField("id", id.toString() , Field.Store.YES));
        document.add(new TextField("firstName", firstName , Field.Store.YES));
        document.add(new TextField("lastName", lastName , Field.Store.YES));
        document.add(new TextField("website", website , Field.Store.YES));
        return document;
    }


    public static List<Document> createUserDocumentList()
    {
        Document document1 = createUserDocument(1, "Lokesh", "Gupta", "howtodoinjava.com");
        Document document2 = createUserDocument(2, "John", "Doe", "example.com");
        Document document3 = createUserDocument(3, "Alice Sarah", "Smith", "samplewebsite.com");
        Document document4 = createUserDocument(4, "Sarah ", "Johnson", "javatutorial.net");
        Document document5 = createUserDocument(5, "Michael", "Brown", "codingexpertise.org");
        Document document6 = createUserDocument(6, "Emily", "Davis", "webdevelopment101.com");
        Document document7 = createUserDocument(7, "David", "Wilson", "javaforbeginners.net");
        Document document8 = createUserDocument(8, "Sarah", "Miller", "programmingbasics.com");
        Document document9 = createUserDocument(9, "Thomas", "Harris", "learnjavaonline.org");
        Document document10 = createUserDocument(10, "Sophia", "Lee", "codingisfun.com");
        return List.of(
                document1, document2, document3, document4, document5,
                document6, document7, document8, document9, document10
        );
    }


}
