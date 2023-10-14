package org.example;

import org.apache.lucene.search.TopDocs;
import org.example.indexer.DocumentFactory;
import org.example.indexer.IndexerService;
import org.example.indexer.SearchManager;

public class Main {

    public static void main(String[] args) throws Exception {
        IndexerService.getInstance().writeDocumentsToTheIndex(DocumentFactory.createUserDocumentList());

        TopDocs topDocs;
        String resulat;


        topDocs = SearchManager.searchById(1);
        resulat = SearchManager.displayResults(topDocs,"id");
        System.out.println(resulat);

        topDocs = SearchManager.searchByFirstName("Sar");
        resulat= SearchManager.displayResults(topDocs,"id");
        System.out.println(resulat);


    }







}