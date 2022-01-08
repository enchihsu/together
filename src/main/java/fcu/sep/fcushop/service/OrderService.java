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

  public List<Order> getOrders(String account) {
    try (Connection connection = sql2oDbHandler.getConnector().open()) {
      String query = "select product.NAME book,product.IMAGE_URL imageUrl,product.PRICE price,order1.amount amount " +
          "from bookstore.product inner join bookstore.order1 on product.ID=order1.BOOK where ACCOUNT = :account";
      System.out.println(query);
      return connection.createQuery(query).addParameter("account", account).executeAndFetch(Order.class);
    }
  }

  public String addcart(String book,String account) {
    try (Connection connection = sql2oDbHandler.getConnector().open()) {
      String query1 = "select count(BOOK) from bookstore.order1 where (ACCOUNT = :account and BOOK = :bookname)";
      var result= connection.createQuery(query1)
          .addParameter("bookname", book)
          .addParameter("account", account)
          .executeScalar(Integer.class);
      if(result==0){
        String query = "insert into bookstore.order1 (BOOK, ACCOUNT, AMOUNT) VALUES(:bookname,:account, 1)";

        System.out.println(query);
        connection.createQuery(query)
            .addParameter("bookname", book)
            .addParameter("account", account)
            .executeUpdate();
      }
      else{
        String query ="Update bookstore.order1 SET AMOUNT = order1.AMOUNT+1  WHERE (BOOK = :bookname and ACCOUNT=:account)";

        System.out.println(query);
        connection.createQuery(query)
            .addParameter("bookname", book)
            .addParameter("account", account)
            .executeUpdate();
      }
    }
    return "Success";
  }

  public String deleteproduct(String book,String account){
    try (Connection connection = sql2oDbHandler.getConnector().open()) {
      String query = "delete FROM bookstore.order1 where (ID=:book and ACCOUNT=:account)";
      connection.createQuery(query)
          .addParameter("book", book)
          .addParameter("account", account)
          .executeUpdate();
      return "Success";
    }
  }

  public String plus(String id,String account){
    try (Connection connection = sql2oDbHandler.getConnector().open()) {
      String query = "UPDATE bookstore.order1 SET AMOUNT=AMOUNT+1 WHERE (ID=:id and ACCOUNT=:account)";
      connection.createQuery(query)
          .addParameter("id", id)
          .addParameter("account", account)
          .executeUpdate();
      return "Success";
    }
  }

  public String minus(String id,String account){
    try (Connection connection = sql2oDbHandler.getConnector().open()) {
      String query = "UPDATE bookstore.order1 SET AMOUNT=AMOUNT-1 WHERE (ID=:id and ACCOUNT=:account)";
      connection.createQuery(query)
          .addParameter("id", id)
          .addParameter("account", account)
          .executeUpdate();
      return "Success";
    }
  }

}

