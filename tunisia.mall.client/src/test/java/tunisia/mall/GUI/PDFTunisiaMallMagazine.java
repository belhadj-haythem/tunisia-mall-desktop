package tunisia.mall.GUI;


import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

import tunisia.mall.businessDelegate.ShopServiceDelegate;
import tunisia.mall.interfaces.NewsServiceRemote;
import tunisia.mall.persistance.News;
import tunisia.mall.persistance.Shop;

import com.itextpdf.text.Image;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfContentByte; 
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfStamper; 


public class PDFTunisiaMallMagazine {
	
	public static boolean main(){
		 Image image1 = null ;
	     Image image2= null ;
	     Image image3= null ;
	     Image image4= null ;
	    int SizeImages=0;
	     List<Image> tabImage = new ArrayList<Image>();
	     List<String> NameArticle = new ArrayList<String>();
	     List<Float> Price = new ArrayList<Float>();
	   //  Image tabImage[] = null;
		try { 
			 
              List<Shop> liste = ShopServiceDelegate.listShop();
              int size = 0;
             
              for (Shop n : liste) {
            	  
            	   byte[] b =n.getPicture();
                   ByteArrayInputStream in = new ByteArrayInputStream(b);
                   BufferedImage read = ImageIO.read(in);
                   image1= Image.getInstance(read, null);
                   
                   tabImage.add(image1);
   
                   NameArticle.add("Description : "+n.getDescription());
                   //Price.add((float) n.getId());
                    SizeImages = tabImage.size();
                   System.out.println("*****************"+SizeImages);
                   size ++ ;
                  }
                

            	 
              
			PdfReader pdfReader = new PdfReader("Z:\\TestFile1.pdf"); 
			
			//- See more at: http://tutorialspointexamples.com/itext-edit-add-modify-write-an-existing-pdf-file-in-java/#sthash.nuD8Fq4a.dpuf
			//Create PdfStamper instance.
			PdfStamper pdfStamper = new PdfStamper(pdfReader, new FileOutputStream("Z:\\TunisiaMallMagazine.pdf")); 
			
			//Create BaseFont instance.
			BaseFont baseFont = BaseFont.createFont( BaseFont.TIMES_ROMAN, BaseFont.CP1252, BaseFont.NOT_EMBEDDED);
			PdfContentByte pageContentByte = pdfStamper.getOverContent(2);
			
			pageContentByte.setFontAndSize(baseFont, 14);
			pageContentByte.beginText();
			
  			int PageNumber = 2 ;
			int PhotoInPage= 4 ;
			boolean Addpage=false;
			int boucle = 0;
			for (Image Img : tabImage) {
				
				if ( Addpage==true ){
						PageNumber++;
						//pdfStamper.ad
						pdfStamper.insertPage(pdfReader.getNumberOfPages() + 1, pdfReader.getPageSizeWithRotation(1));
						pageContentByte = pdfStamper.getOverContent(PageNumber);
						PhotoInPage=1;
						
				}
				
				if (PhotoInPage==1 ){
					BaseFont baseFont1 = BaseFont.createFont( baseFont.TIMES_ROMAN, BaseFont.CP1252, BaseFont.NOT_EMBEDDED);
				//Img.setWidthPercentage(600);
				//Img.scalePercent(50);
				//Img.scaleAbsolute(400, 400);
					Img.scaleAbsolute(1100,1300);
				Img.setAbsolutePosition(-6f, 0f); 
				Img.setWidthPercentage(80);
				Img.scalePercent(50);
				pageContentByte.setFontAndSize(baseFont1,14);
				pageContentByte.setTextMatrix(300f, 500f);
				
				pageContentByte.showText(NameArticle.get(boucle));
			    pageContentByte.addImage(Img);}
				if (PhotoInPage==2 ){
					BaseFont baseFont1 = BaseFont.createFont( BaseFont.TIMES_ROMAN, BaseFont.CP1252, BaseFont.NOT_EMBEDDED);
					pageContentByte.setFontAndSize(baseFont1, 14);
					Img.setWidthPercentage(80);
					Img.scalePercent(50);
					Img.setAbsolutePosition(-10f,400f);
					
				    pageContentByte.addImage(Img);
				    pageContentByte.setTextMatrix(300f,400f);
					
					pageContentByte.showText(NameArticle.get(boucle));
					
				}
				if (PhotoInPage==3 ){
					BaseFont baseFont1 = BaseFont.createFont( BaseFont.TIMES_ROMAN, BaseFont.CP1252, BaseFont.NOT_EMBEDDED);
					pageContentByte.setFontAndSize(baseFont1, 14);
					Img.setWidthPercentage(80);
					Img.scalePercent(50);
					Img.setAbsolutePosition(-6f,0f);
					
				    pageContentByte.addImage(Img);
				    pageContentByte.setTextMatrix(300f,0f);
					
					pageContentByte.showText(NameArticle.get(boucle)); 
					
				}
				if (PhotoInPage==4){
					
					Img.setWidthPercentage(80);
					Img.scalePercent(50);
					Img.setAbsolutePosition(-6f,0f); 
				    pageContentByte.addImage(Img);
				    pageContentByte.addImage(Img);
				    pageContentByte.setTextMatrix(300f,0f);
					
				    Addpage=true;
				   
				}
				PhotoInPage++;
			boucle++;
				
				
				
			}
			
			pageContentByte.endText();
			pdfStamper.close();
			return true;
			
		}
		
		catch (Exception e) { e.printStackTrace();
		return false;
		}
	}
}