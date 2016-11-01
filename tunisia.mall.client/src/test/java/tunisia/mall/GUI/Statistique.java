package tunisia.mall.GUI;

import static java.lang.Integer.parseInt;

import org.junit.Test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
//import tunisia.mall.tests.TestCart;
import tunisia.mall.businessDelegate.CartServiceDelegate;


  public class Statistique {
   
               @SuppressWarnings("restriction")
			Scene scene1 = new Scene(new Group(),700,400);
               CartServiceDelegate cartservicedelegate;
              
       
         public int nbrlistcart(int id){
        	 int i = cartservicedelegate.listCartByIdShop(id).size();
        	 return i;
         }
          long j = cartservicedelegate.countItems(2000);
          
 public ObservableList<PieChart.Data>  stat(){
        ObservableList<PieChart.Data> pieChartData =
                FXCollections.observableArrayList();
    pieChartData.addAll( new PieChart.Data("Sold Product",
      nbrlistcart(2000)),new PieChart.Data("Availble Product",j)) ;
               
                

return  pieChartData;
 }
    

}
