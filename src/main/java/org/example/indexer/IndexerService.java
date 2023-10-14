package org.example.indexer;

import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.example.utils.Constant;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;

public final class IndexerService {

    private IndexWriter indexWriter;
    private IndexSearcher indexSearcher;
    private static IndexerService instance = new IndexerService();

    private IndexerService(){
        try {
            this.createWriterIndex();
            this.createSearcher();
        }catch (Exception e){
            System.out.println(e);
        }

    }

    public static IndexerService getInstance() {
        return instance;
    }

    public IndexWriter getIndexWriter() {
        return indexWriter;
    }

    public IndexSearcher getIndexSearcher() {
        return indexSearcher;
    }

    public void writeDocumentsToTheIndex(List<Document> documents){
        try {
            this.indexWriter.deleteAll();
            this.indexWriter.addDocuments(documents);
            this.indexWriter.commit();
            this.indexWriter.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    private void createWriterIndex() throws IOException {
        FSDirectory dir = FSDirectory.open(Paths.get(Constant.DirectoryName));
        IndexWriterConfig config = new IndexWriterConfig(new StandardAnalyzer());
        this.indexWriter = new IndexWriter(dir, config);
    }


    private void createSearcher() throws IOException
    {
        Directory dir = FSDirectory.open(Paths.get(Constant.DirectoryName));
        IndexReader reader = DirectoryReader.open(dir);
        this.indexSearcher = new IndexSearcher(reader);
    }
}
