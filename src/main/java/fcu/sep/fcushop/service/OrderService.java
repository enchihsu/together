package fcu.sep.fcushop.service;

import fcu.sep.fcushop.database.Sql2oDbHandler;
import fcu.sep.fcushop.model.Order;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import org.sql2o.Connection;
import org.w3c.dom.html.HTMLBRElement;

@Service
public class OrderService {

  @Autowired
  private Sql2oDbHandler sql2oDbHandler;

  public OrderService() {

  }
  /**
   * 点对 (x,y) 的水平和垂直距离.
   */

  public List<Order> getOrders() {
    try (Connection connection = sql2oDbHandler.getConnector().open()) {
      String query = "select product.NAME book,product.IMAGE_URL imageUrl,product.PRICE price,order.amount amount from  bookstore.product inner join bookstore.order on product.ID=order.BOOK";

      return connection.createQuery(query).executeAndFetch(Order.class);
    }
  }

  /*public String addcart(String book) {
    try (Connection connection = sql2oDbHandler.getConnector().open()) {
      String query = "insert into bookstore.order (BOOK, ACCOUNT, AMOUNT) VALUES(:book, 0912345678, 1)";

      System.out.println(query);
      connection.createQuery(query)
          .addParameter("book", book)
          //.addParameter("quantity", quantity) 人
          .executeUpdate();
      return "Success";
  }
}*/
  public String addcart(String book) {
    try (Connection connection = sql2oDbHandler.getConnector().open()) {
      String query1 = "select ID from bookstore.order where (order.ACCOUNT = 0912345678 and order.BOOK = :bookname)";
      var result =  connection.createQuery(query1).addParameter("bookname", book).executeAndFetch(Order.class);
      if(result==null){
        String query = "insert into bookstore.order (BOOK, ACCOUNT, AMOUNT) VALUES(:bookname, 0912345678, 1)";

        System.out.println(query);
        connection.createQuery(query)
            .addParameter("bookname", book)
            //.addParameter("quantity", quantity) 人
            .executeUpdate();
      }
      else{
        String query ="Update bookstore.order SET AMOUNT = order.AMOUNT+1  WHERE (BOOK = :bookname and ACCOUNT=0912345678)";

        System.out.println(query);
        connection.createQuery(query)
            .addParameter("bookname", book)
            //.addParameter("quantity", quantity) 人
            .executeUpdate();
      }
    }
    return "Success";
  }
}
