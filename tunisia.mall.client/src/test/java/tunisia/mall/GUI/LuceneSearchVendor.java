package tunisia.mall.GUI;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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

import tunisia.mall.businessDelegate.VendorServiceDelegate;
import tunisia.mall.persistance.Vendor;

public class LuceneSearchVendor {
	
	public static  List<Vendor> lucene(String querystr)
	{
		List<Vendor> searchResult = new ArrayList<Vendor>();
		try
		{
			//	Specify the analyzer for tokenizing text.
		    //	The same analyzer should be used for indexing and searching
			StandardAnalyzer analyzer = new StandardAnalyzer();
			
			//	Code to create the index
			Directory index = new RAMDirectory();
			
			IndexWriterConfig config = new IndexWriterConfig(analyzer);
			
			List<Vendor> l = VendorServiceDelegate.listVendor(Connexion.shopKriaa.getId_account());
			IndexWriter w = new IndexWriter(index, config);
			
			for(Vendor v : l){
				addDoc(w,v);
			}
			w.close();
			
			//	Text to search
			//String querystr ="uce";
			
			//	The \"title\" arg specifies the default field to use when no field is explicitly specified in the query
			//Query q = new QueryParser( "title",analyzer).parse(querystr);
			Query q = new WildcardQuery(new Term("firstName","*"+querystr+"*"));
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
		      Vendor vendor = new Vendor();
		      vendor.setFirst_name(d.get("firstName"));
		      vendor.setLast_name(d.get("lastName"));
		      vendor.setSalary(Float.parseFloat(d.get("salary")));
		      vendor.setIdCard(Integer.parseInt(d.get("idCard")));
		      DateFormat formatter ; 
		      Date date ; 
		      formatter = new SimpleDateFormat("yyyy-MM-dd");
		      date = formatter.parse(d.get("date"));
		      vendor.setDateEmbauche(date);
		      searchResult.add(vendor);
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
	private static void addDoc(IndexWriter w, Vendor vendor) throws IOException 
	{
		  Document doc = new Document();
		  // A text field will be tokenized
		  doc.add(new TextField("firstName", vendor.getFirst_name(), Field.Store.YES));
		  doc.add(new TextField("lastName", vendor.getLast_name(), Field.Store.YES));
		  doc.add(new TextField("salary", vendor.getSalary()+"", Field.Store.YES));
		  doc.add(new TextField("idCard", vendor.getIdCard()+"", Field.Store.YES));
		  doc.add(new TextField("date", vendor.getDateEmbauche().toString(), Field.Store.YES));
		  w.addDocument(doc);
	}
	
}


