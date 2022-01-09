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
          "from bookstore.product inner join bookstore.order1 on product.ID=order1.BOOK where order1.ACCOUNT = :account";
      System.out.println(query);
      return connection.createQuery(query).addParameter("account", account).executeAndFetch(Order.class);
    }
  }

  public String addcart(Integer book,String account) {
    try (Connection connection = sql2oDbHandler.getConnector().open()) {
      String query3 = "select product.ID from bookstore.product";
      var result3= connection.createQuery(query3)
          .executeScalarList(Integer.class);
      System.out.println("booklist:"+result3);
      var bookid= result3.get(book-1);
      System.out.println("bookid:"+bookid);

      String query1 = "select count(BOOK) from bookstore.order1 where (ACCOUNT = :account and BOOK = :book)";
      var result= connection.createQuery(query1)
          .addParameter("book", bookid)
          .addParameter("account", account)
          .executeScalar(Integer.class);
      System.out.println("count:"+result);
      if(result==0){
        String query = "insert into bookstore.order1 (BOOK, ACCOUNT, AMOUNT) VALUES(:bookname, :account, 1)";

        System.out.println(query);
        connection.createQuery(query)
            .addParameter("bookname", bookid)
            .addParameter("account", account)
            .executeUpdate();
      }
      else{
        String query ="Update bookstore.order1 SET AMOUNT = order1.AMOUNT+1  WHERE (BOOK = :bookname and ACCOUNT=:account)";

        System.out.println(query);
        connection.createQuery(query)
            .addParameter("bookname", bookid)
            .addParameter("account", account)
            .executeUpdate();
      }
    }
    return "Success";
  }

  public String deleteproduct(Integer book,String account){
    try (Connection connection = sql2oDbHandler.getConnector().open()) {
      String query1 = "select order1.BOOK from bookstore.order1 where (ACCOUNT = :account)";
      var result= connection.createQuery(query1)
          .addParameter("account", account)
          .executeScalarList(Integer.class);
      System.out.println("result:"+result);
      var bookid= result.get(book-1);
      System.out.println("bookid:"+bookid);
      String query = "delete FROM bookstore.order1 where (BOOK=:book and ACCOUNT=:account)";
      connection.createQuery(query)
          .addParameter("book", bookid)
          .addParameter("account", account)
          .executeUpdate();
      return "Success";
    }
  }

  public String plus(Integer id,String account){
    try (Connection connection = sql2oDbHandler.getConnector().open()) {
      String query1 = "select order1.BOOK from bookstore.order1 where (ACCOUNT = :account)";
      var result= connection.createQuery(query1)
          .addParameter("account", account)
          .executeScalarList(Integer.class);
      System.out.println("result:"+result);
      var bookid= result.get(id-1);
      System.out.println("bookid:"+bookid);
      String query = "UPDATE bookstore.order1 SET AMOUNT=AMOUNT+1 WHERE (BOOK=:id and ACCOUNT=:account)";
      connection.createQuery(query)
          .addParameter("id", bookid)
          .addParameter("account", account)
          .executeUpdate();
      return "Success";
    }
  }

  public String minus(Integer id,String account){
    try (Connection connection = sql2oDbHandler.getConnector().open()) {
      String query1 = "select order1.BOOK from bookstore.order1 where (ACCOUNT = :account)";
      var result= connection.createQuery(query1)
          .addParameter("account", account)
          .executeScalarList(Integer.class);
      System.out.println("result:"+result);
      var bookid= result.get(id-1);
      System.out.println("bookid:"+bookid);
      String query2 = "select order1.AMOUNT from bookstore.order1 where (ACCOUNT = :account and BOOK=:bookid)";
      var result1= connection.createQuery(query2)
          .addParameter("account", account)
          .addParameter("bookid", bookid)
          .executeScalar(Integer.class);
      System.out.println("result1:"+result1);
      if(result1==1){
        String query = "delete FROM bookstore.order1 where (BOOK=:book and ACCOUNT=:account)";
        connection.createQuery(query)
            .addParameter("book", bookid)
            .addParameter("account", account)
            .executeUpdate();
      }
      else {
        String query = "UPDATE bookstore.order1 SET AMOUNT=AMOUNT-1 WHERE (BOOK=:id and ACCOUNT=:account)";
        connection.createQuery(query)
            .addParameter("id", bookid)
            .addParameter("account", account)
            .executeUpdate();
      }
      return "Success";
    }
  }

}