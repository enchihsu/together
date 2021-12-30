package fcu.sep.fcushop.service;

import fcu.sep.fcushop.database.Sql2oDbHandler;
import fcu.sep.fcushop.model.Order;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

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
      String query = "select product.NAME book,product.IMAGE_URL imageUrl,product.PRICE price,order1.amount amount from bookstore.product inner join bookstore.order1 on product.ID=order1.BOOK";
      return connection.createQuery(query).executeAndFetch(Order.class);
    }
  }

  public String addcart(String book) {
    try (Connection connection = sql2oDbHandler.getConnector().open()) {
      String query1 = "select count(BOOK) from bookstore.order1 where (ACCOUNT = 0912345678 and BOOK = :bookname)";
      var result= connection.createQuery(query1).addParameter("bookname", book).executeScalar(Integer.class);
      if(result==0){
        String query = "insert into bookstore.order1 (BOOK, ACCOUNT, AMOUNT) VALUES(:bookname, 0912345678, 1)";

        System.out.println(query);
        connection.createQuery(query)
            .addParameter("bookname", book)
            //.addParameter("quantity", quantity) 人
            .executeUpdate();
      }
      else{
        String query ="Update bookstore.order1 SET AMOUNT = order1.AMOUNT+1  WHERE (BOOK = :bookname and ACCOUNT=0912345678)";

        System.out.println(query);
        connection.createQuery(query)
            .addParameter("bookname", book)
            //.addParameter("quantity", quantity) 人
            .executeUpdate();
      }
    }
    return "Success";
  }

    public String deleteproduct(String bookname){
    try (Connection connection = sql2oDbHandler.getConnector().open()) {
      String query = "delete FROM bookstore.order1 where ID=:bookname";
      connection.createQuery(query)
          .addParameter("bookname", bookname)
          .executeUpdate();
      return "Success";
    }
  }

  public String plus(String id){
    try (Connection connection = sql2oDbHandler.getConnector().open()) {
      String query = "UPDATE bookstore.order1 SET AMOUNT=AMOUNT+1 WHERE ID=:id";
      connection.createQuery(query)
          .addParameter("id", id)
          .executeUpdate();
      return "Success";
    }
  }

  public String minus(String id){
    try (Connection connection = sql2oDbHandler.getConnector().open()) {
      String query = "UPDATE bookstore.order1 SET AMOUNT=AMOUNT-1 WHERE ID=:id";
      connection.createQuery(query)
          .addParameter("id", id)
          .executeUpdate();
      return "Success";
    }
  }

}

