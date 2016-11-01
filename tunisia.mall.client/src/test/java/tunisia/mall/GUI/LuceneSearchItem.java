package tunisia.mall.GUI;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.directory.SearchResult;

import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.StringField;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.index.Term;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.BooleanClause.Occur;
import org.apache.lucene.search.BooleanQuery;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopScoreDocCollector;
import org.apache.lucene.search.WildcardQuery;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.RAMDirectory;
import org.apache.lucene.util.Version;

import tunisia.mall.businessDelegate.ItemServiceDelegate;
import tunisia.mall.persistance.Item;

public class LuceneSearchItem {
	
	public static  List<Item> lucene(String querystr)
	{
		List<Item> searchResult = new ArrayList<Item>();
		try
		{
			//	Specify the analyzer for tokenizing text.
		    //	The same analyzer should be used for indexing and searching
			StandardAnalyzer analyzer = new StandardAnalyzer();
			
			//	Code to create the index
			Directory index = new RAMDirectory();
			
			IndexWriterConfig config = new IndexWriterConfig(analyzer);
			
			List<Item> l = ItemServiceDelegate.listItems( Connexion.shopKriaa.getId_account());
			IndexWriter w = new IndexWriter(index, config);
			
			for(Item i : l){
				addDoc(w,i);
			}
			w.close();
			
			//	Text to search
			//String querystr ="uce";
			
			//	The \"title\" arg specifies the default field to use when no field is explicitly specified in the query
			//Query q = new QueryParser( "title",analyzer).parse(querystr);
			Query q = new WildcardQuery(new Term("reference","*"+querystr+"*"));
			//BooleanQuery pp = new BooleanQuery();
			//pp.add(q, Occur.SHOULD);
			
			
			
			// Searching code
			int hitsPerPage = 10;
		    IndexReader reader = DirectoryReader.open(index);
		    IndexSearcher searcher = new IndexSearcher(reader);
		    TopScoreDocCollector collector = TopScoreDocCollector.create(hitsPerPage);
		    searcher.search(q, collector);
		    ScoreDoc[] hits = collector.topDocs().scoreDocs;
		    
		    //	Code to display the results of search
		    System.out.println("Found " + hits.length + " hits.");
		     
		    for(int i=0;i<hits.length;++i) 
		    {
		      int docId = hits[i].doc;
		      Document d = searcher.doc(docId);
		      System.out.println((i + 1) + ". " + d.get("name")+ d.get("description")+ d.get("reference")+ d.get("price"));
		      Item item = new Item();
		      item.setName(d.get("name"));
		      item.setDescription(d.get("description"));
		      item.setReference(d.get("reference"));
		      item.setPrice(Float.parseFloat(d.get("price")));
		      searchResult.add(item);
		    }
		    
		    // reader can only be closed when there is no need to access the documents any more
		    reader.close();
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
		}
		return searchResult;
	}
	private static void addDoc(IndexWriter w, Item item) throws IOException 
	{
		  Document doc = new Document();
		  // A text field will be tokenized
		  doc.add(new TextField("name", item.getName(), Field.Store.YES));
		  doc.add(new TextField("description", item.getDescription(), Field.Store.YES));
		  doc.add(new TextField("reference", item.getReference(), Field.Store.YES));
		  doc.add(new TextField("price", item.getPrice()+"", Field.Store.YES));
		  w.addDocument(doc);
	}
	
}


