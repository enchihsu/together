package fcu.sep.fcushop.service;

import fcu.sep.fcushop.database.Sql2oDbHandler;
import fcu.sep.fcushop.model.Product;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import org.sql2o.Connection;

/**
 * productservice.
 */

@Service
public class ProductService {

  @Autowired
  private Sql2oDbHandler sql2oDbHandler;

  public ProductService() {

  }

  /**
   * productservice.
   */

  public List<Product> getProducts() {
    try (Connection connection = sql2oDbHandler.getConnector().open()) {
      String query = "select ID id,NAME name,IMAGE_URL imageUrl,"
          + "PRICE price, QUANTITY quantity,DESCRIPTION description"
          + " from bookstore.product";

      return connection.createQuery(query).executeAndFetch(Product.class);
    }
  }


  /**
   * productservice.
   */

  public List<Product> getProducts(String keyword) {
    try (Connection connection = sql2oDbHandler.getConnector().open()) {
      String query = "select ID id, NAME name, IMAGE_URL imageUrl,"
          + "PRICE price, QUANTITY quantity, DESCRIPTION description"
          + " from bookstore.product where name like :keyword";

      return connection.createQuery(query)
          .addParameter("keyword", "%" + keyword + "%")
          .executeAndFetch(Product.class);
    }
  }

  /**
   * productservice.
   */

  public String aaAddProduct(String bookname, String imgurl, int price,
                           int quantity, String description) {
    try (Connection connection = sql2oDbHandler.getConnector().open()) {
      String query = "insert into "
          + "bookstore.product (NAME, IMAGE_URL, PRICE, QUANTITY, DESCRIPTION) "
          + "VALUES(:book_name, :img_url, :price, :quantity, :description)";

      System.out.println(query);
      connection.createQuery(query)
          .addParameter("book_name", bookname)
          .addParameter("img_url", imgurl)
          .addParameter("price", price)
          .addParameter("quantity", quantity)
          .addParameter("description", description)
          .executeUpdate();
      return "Success";
    }
  }

  /**
   * productservice.
   */

  public String aaUpdateProduct(String bookname, int price) {
    try (Connection connection = sql2oDbHandler.getConnector().open()) {
      String query = "Update bookstore.product "
          + "SET PRICE= :price WHERE NAME = :book_name";

      System.out.println(query);
      connection.createQuery(query)
          .addParameter("book_name", bookname)
          .addParameter("price", price)
          .executeUpdate();
      return "Success";

    }
  }

}