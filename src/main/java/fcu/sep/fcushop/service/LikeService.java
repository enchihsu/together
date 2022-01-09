package fcu.sep.fcushop.service;

import fcu.sep.fcushop.database.Sql2oDbHandler;
import fcu.sep.fcushop.model.Like;
import fcu.sep.fcushop.model.Order;
import fcu.sep.fcushop.model.Product;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.sql2o.Connection;

/**
 * likeservice.
 */


@Service
public class LikeService {

  @Autowired
  private Sql2oDbHandler sql2oDbHandler;

  public LikeService() {

  }

  /**
   * likeservice.
   */

  public List<Like> getLikes(String account) {
    try (Connection connection = sql2oDbHandler.getConnector().open()) {
      String query = "select product.PRICE price, product.NAME name, product.IMAGE_URL imageUrl, like1.QUANTITY quantity\n"
          + "    FROM bookstore.product\n"
          + "    INNER JOIN bookstore.like1\n"
          + "    ON ((bookstore.product.ID=bookstore.like1.BOOK) and like1.ACCOUNT = :account);";
      //System.out.println(query);
      return connection.createQuery(query).addParameter("account", account).executeAndFetch(Like.class);
    }
  }

  /**
   * likeservice.
   */

  public String addlike(Integer like, String account) {
    try (Connection connection = sql2oDbHandler.getConnector().open()) {
      String query2 = "select product.ID from bookstore.product";
      var result1 = connection.createQuery(query2)
          .executeScalarList(Integer.class);
      System.out.println("result:" + result1);
      var book = result1.get(like - 1);
      System.out.println("book:" + book);

      String query1 = "select count(BOOK) from bookstore.like1 where (ACCOUNT = :account and BOOK = :bookname)";
      var result = connection.createQuery(query1)
          .addParameter("bookname", book)
          .addParameter("account", account)
          .executeScalar(Integer.class);
      if (result == 0) {
        String query = "insert into bookstore.like1 (BOOK, ACCOUNT) VALUES(:bookname, :account)";
        System.out.println(query);
        connection.createQuery(query)
            .addParameter("bookname", book)
            .addParameter("account", account)
            .executeUpdate();
      } else {
        return "S";
      }
    }
    return "Success";
  }

  /**
   * likeservice.
   */

  public String addliketocart(Integer id, String account) {
    try (Connection connection = sql2oDbHandler.getConnector().open()) {
      String query1 = "select like1.BOOK from bookstore.like1 where (ACCOUNT = :account)";
      var result = connection.createQuery(query1)
          .addParameter("account", account)
          .executeScalarList(Integer.class);
      System.out.println("result:" + result);
      var book = result.get(id - 1);
      System.out.println("book:" + book);
      String query2 = "select count(BOOK) from bookstore.order1 where (ACCOUNT = :account and BOOK = :book)";
      var result2 = connection.createQuery(query2)
          .addParameter("account", account)
          .addParameter("book", book)
          .executeScalar(Integer.class);
      System.out.println("count:" + result2);

      if (result2 == 0) {
        String query = "insert into bookstore.order1 (BOOK, ACCOUNT, AMOUNT) VALUES(:book, :account, 1)";
        System.out.println(query);
        connection.createQuery(query)
            .addParameter("account", account)
            .addParameter("book", book)
            .executeUpdate();
        System.out.println("a:" + book);
        String query3 = "DELETE FROM bookstore.like1 WHERE ACCOUNT=:account and BOOK= :book;";
        connection.createQuery(query3)
            .addParameter("account", account)
            .addParameter("book", book)
            .executeUpdate();
      } else {
        String query5 = "select product.QUANTITY from product where product.ID = :book;";
        var quantity = connection.createQuery(query5)
            .addParameter("book", book)
            .executeScalar(Integer.class);
        System.out.println("quantity:" + quantity);
        String query4 = "select AMOUNT from bookstore.order1 where BOOK = :book and ACCOUNT =:account";
        var amount = connection.createQuery(query4)
            .addParameter("account",account)
            .addParameter("book", book)
            .executeScalar(Integer.class);
        System.out.println("amount:" + amount);
        if (quantity > amount) {
          String query = "Update bookstore.order1 SET AMOUNT = order1.AMOUNT+1  WHERE (BOOK = :book and ACCOUNT=:account)";
          System.out.println(query);
          connection.createQuery(query)
              .addParameter("account", account)
              .addParameter("book", book)
              .executeUpdate();
          System.out.println("b:" + book);
          String query3 = "DELETE FROM bookstore.like1 WHERE ACCOUNT=:account and BOOK= :book;";
          connection.createQuery(query3)
              .addParameter("account", account)
              .addParameter("book", book)
              .executeUpdate();
        } else {
          return "no";
        }
      }
    }
    return "Success";
  }
}