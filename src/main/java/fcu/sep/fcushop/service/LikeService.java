package fcu.sep.fcushop.service;

import fcu.sep.fcushop.database.Sql2oDbHandler;
import fcu.sep.fcushop.model.Like;
import fcu.sep.fcushop.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.sql2o.Connection;

import java.util.ArrayList;
import java.util.List;


@Service
public class LikeService {

  @Autowired
  private Sql2oDbHandler sql2oDbHandler;

  public LikeService() {

  }

  public List<Like> getLikes() {
    try (Connection connection = sql2oDbHandler.getConnector().open()) {
      String query = "select product.PRICE price, product.NAME name, product.IMAGE_URL imageUrl, like1.QUANTITY quantity\n" +
          "    FROM bookstore.product\n" +
          "    INNER JOIN bookstore.like1\n" +
          "    ON ((bookstore.product.ID=bookstore.like1.BOOK) and like1.ACCOUNT=0912345678);";

      return connection.createQuery(query).executeAndFetch(Like.class);
    }
  }

  public String addlike(String like) {
    try (Connection connection = sql2oDbHandler.getConnector().open()) {
      String query1 = "select count(BOOK) from bookstore.like1 where (ACCOUNT = 0912345678 and BOOK = :bookname)";
      var result= connection.createQuery(query1).addParameter("bookname", like).executeScalar(Integer.class);
      if(result==0){
        String query = "insert into bookstore.like1 (BOOK, ACCOUNT) VALUES(:bookname, 0912345678)";

        System.out.println(query);
        connection.createQuery(query)
            .addParameter("bookname", like)
            //.addParameter("quantity", quantity) 人
            .executeUpdate();
        return "Success";
      }
      else{
        return "S";
      }
    }
  }

  public String addliketocart(Integer id) {
    try (Connection connection = sql2oDbHandler.getConnector().open()) {
      String query1 = "select like1.BOOK from bookstore.like1 where (ACCOUNT = 0912345678)";
      var result= connection.createQuery(query1)
          .executeScalarList(Integer.class);
      var book= result.get(id-1);
      System.out.println("book:"+book);
      String query2 = "select count(BOOK) from bookstore.order1 where (ACCOUNT = 0912345678 and BOOK = :book)";
      var result2= connection.createQuery(query2)
          .addParameter("book",book)
          .executeScalar(Integer.class);
      System.out.println("count:"+result2);

      if(result2==0){
        String query = "insert into bookstore.order1 (BOOK, ACCOUNT, AMOUNT) VALUES(book, 0912345678, 1)";
        System.out.println(query);
        connection.createQuery(query)
            .executeUpdate();
        System.out.println("a:"+book);
      }
      else{
        String query ="Update bookstore.order1 SET AMOUNT = order1.AMOUNT+1  WHERE (BOOK = book and ACCOUNT=0912345678)";
        System.out.println(query);
        connection.createQuery(query)
            .executeUpdate();
        System.out.println("b:"+book);
      }
    }
    return "Success";
  }
}
