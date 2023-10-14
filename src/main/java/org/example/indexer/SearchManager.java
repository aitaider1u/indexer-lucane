package org.example.indexer;

import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.Term;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.FuzzyQuery;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.example.utils.Constant;

import java.io.IOException;

public class SearchManager {
    public static TopDocs searchById(Integer id)
    {
        QueryParser qp = new QueryParser("id", new StandardAnalyzer());
        try {
            Query idQuery = qp.parse(id.toString());
            return  IndexerService.getInstance().getIndexSearcher().search(idQuery, Constant.topN);
        } catch (ParseException | IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static TopDocs searchByFirstName(String firstname) throws Exception
    {
        QueryParser qp = new QueryParser("firstName", new StandardAnalyzer());
        try {
            Query query = qp.parse(firstname+"~"); // The "~" indicates a fuzzy search
            return  IndexerService.getInstance().getIndexSearcher().search(query, Constant.topN);
        } catch (ParseException | IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static String displayResults(TopDocs topDocs, String field){
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Total Results : ").append(topDocs.totalHits).append("\n");
        for (ScoreDoc sd : topDocs.scoreDocs)
        {
            try {
                Document d = IndexerService.getInstance().getIndexSearcher().doc(sd.doc);
                stringBuilder.append(String.format(d.get(field))).append("\n");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return stringBuilder.toString();
    }
}
