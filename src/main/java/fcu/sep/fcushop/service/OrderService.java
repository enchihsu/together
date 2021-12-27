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
      String query = "select ID id, ACCOUNT account, BOOK book,IMAGE_URL imageUrl, PRICE price, AMOUNT amount"
          + " from bookstore.order1";

      return connection.createQuery(query).executeAndFetch(Order.class);
    }
  }

  /*public String addCart(String book_name)
  {
    try (Connection connection = sql2oDbHandler.getConnector().open()) {
      String query = "insert into bookstore.order (NAME, ACCOUNT, AMOUNT) VALUES(:book_name, 0912345678, 1)";

      System.out.println(query);
      connection.createQuery(query)
          .addParameter("book_name", book_name)
          //.addParameter("quantity", quantity) 人
          //.addParameter("quantity", quantity)
          //.addParameter("img_url", img_url)
          //.addParameter("price", price)
          //.addParameter("description", description)
          .executeUpdate();
      return "Success";
    }
  }*/

  public String addcart(String book) {
    try (Connection connection = sql2oDbHandler.getConnector().open()) {
      String query = "insert into bookstore.order (BOOK, ACCOUNT, AMOUNT) VALUES(:bookname, 0912345678, 1)";

      System.out.println(query);
      connection.createQuery(query)
          .addParameter("bookname", book)
          //.addParameter("quantity", quantity) 人
          //.addParameter("quantity", quantity)
          //.addParameter("img_url", img_url)
          //.addParameter("price", price)
          //.addParameter("description", description)
          .executeUpdate();
      return "Success";
  }
}
}
 /* public String InsertIntoCar1(long id) {
    try (Connection connection = sql2oDbHandler.getConnector().open()) {
      String query = "Insert into project.productcar(PRODUCT_NAME,INSTOCK,PRICE,QUANTITY,PICTURE) select PRODUCT_NAME,INSTOCK,PRICE,QUANTITY,PICTURE FROM project.product where PRODUCT_ID = :id";

      System.out.println(query);
      connection.createQuery(query)
          .addParameter("id", id)
          .executeUpdate();

    }
    return "success";
  }*/